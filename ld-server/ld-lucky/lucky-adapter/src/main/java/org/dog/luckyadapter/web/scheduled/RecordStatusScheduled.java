package org.dog.luckyadapter.web.scheduled;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.activity.cmd.RedisDeductionAwardNumberDrawExe;
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
     * 每隔五分钟执行一次
     */
//    @Scheduled(cron = "0 0/5 * * * ?")
//    @DistributedLock
    public void deductionOfInventoryAndUpdateRecordStatus() {
        drawExe.ScheduledExecuteDeductionOfInventoryAndUpdateRecordStatus();
    }

//    @DistributedLock(key = "test")
//    @Scheduled(cron = "0/5 * * * * ?")
    public void test() {

//        try {
            System.out.println("业务处理中");
//            Thread.sleep(1 * 60 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

}
