package org.dog.luckyadapter.web.scheduled;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.DistributedLock;
import org.dog.luckyapp.activity.cmd.RedisDeductionAwardNumberDrawExe;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/20 23:01
 * @Description:
 */

@Component
@Slf4j
@AllArgsConstructor
public class RecordStatusScheduled {

    private final RedisDeductionAwardNumberDrawExe drawExe;

    /**
     * 每个5分钟执行一次
     */
    @DistributedLock
    @Scheduled(cron = "0 0/5 * * * ?")
    public void deductionOfInventoryAndUpdateRecordStatus() {
        /**
         * 定时扫描用户不可见状态的中奖记录，然后对比当前时间和数据创建时间，
         * 发现两者相隔 5 分钟，那么，定时任务就可以把这个记录查询出来，再来执行一边，方案三消费者流程
         */
        drawExe.ScheduledExecuteDeductionOfInventoryAndUpdateRecordStatus();
    }

}
