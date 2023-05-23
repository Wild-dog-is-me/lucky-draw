package org.dog.common.lock.torenew;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.lock.DistributedLockTask;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Odin
 * @Date: 2023/5/24 01:46
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class DistributedLockToRenew {

    private final RedisTemplate<String, Object> redisTemplate;

    public static final List<DistributedLockTask> taskList = new ArrayList<>();

    private ScheduledExecutorService taskExecutorService;

    {
        taskExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

        taskExecutorService.scheduleAtFixedRate(() -> {
            try {
                scanningTask();
            } catch (Exception e) {
                //错误处理
                log.error("执行扫描Task逻辑出错：", e);
            }
        }, 1, 4, TimeUnit.SECONDS);
    }

    private void scanningTask() {
        if (CollUtil.isEmpty(taskList)) {
            return;
        }
        Iterator<DistributedLockTask> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            DistributedLockTask task = iterator.next();

            try {
                // 判断Redis中是否存在key
                if (Boolean.FALSE.equals(redisTemplate.hasKey(task.getKey()))) {
                    iterator.remove();
                    continue;
                }

                // 判断是否达到最大续约次数
                if (Boolean.FALSE.equals(task.isMaxToRenewNum(null))) {
                    // 把耗时任务中断，排除业务执行耗时很久
                    task.getThread().interrupt();
                    iterator.remove();
                    continue;
                }

                // 判断是否到达续约时间
                if (Boolean.FALSE.equals(task.isToRenewTime(null))) {
                    continue;
                }

                log.info("开始续约任务：key:{}", task.getKey());
                redisTemplate.expire(task.getKey(), task.getExpiredTime(), TimeUnit.SECONDS);
                task.setNewToRenewNum(task.getNewToRenewNum() + 1);
                task.setNewUpdatedTime(LocalDateTime.now());
            } catch (Exception e) {
                //错误处理
                log.error("处理任务出错：{}，", JSON.toJSONString(task), e);
            }
        }
    }

}
