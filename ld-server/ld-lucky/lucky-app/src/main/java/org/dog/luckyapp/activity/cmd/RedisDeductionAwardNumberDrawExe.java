package org.dog.luckyapp.activity.cmd;

import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.listener.AwardInventoryToRedisApplicationListener;
import org.dog.luckydomain.gateway.AwardGateway;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/16 08:18
 * @Description:
 */

@Slf4j
@Component
public class RedisDeductionAwardNumberDrawExe extends DefaultDrawExe {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisDeductionAwardNumberDrawExe(
            AwardGateway awardGateway,
            PrizeGateway prizeGateway,
            RedisTemplate<String, Object> redisTemplate
    ) {
        super(awardGateway, prizeGateway);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Integer defaultDeductionAwardNumber(Long activityId, Long awardId) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        // Lua脚本
        redisScript.setScriptText("");
        // 返回执行后的库存
        redisScript.setResultType(Long.class);
        Long execute = redisTemplate.opsForValue().getOperations().execute(
                redisScript,
                List.of(AwardInventoryToRedisApplicationListener.getKey(activityId, awardId)));
        // -1 表明库存扣减失败
        if (execute != -1) {
            // 失败
            return 0;
        }
        // 成功

        // 插入不可见记录

        return 1;
    }
}
