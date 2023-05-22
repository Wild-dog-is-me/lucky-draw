package org.dog.luckyapp.mq.consume;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.dog.config.dto.ActivityDrawMessage;
import org.dog.config.util.AssertUtil;
import org.dog.luckyapp.activity.cmd.RedisDeductionAwardNumberDrawExe;
import org.dog.luckyapp.context.ActivityDrawContext;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/18 10:32
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
@RocketMQMessageListener(topic = "activity-draw-sendTest-topic",consumerGroup = "lucky_draw")
public class ActivityDrawMessageConsume implements RocketMQListener<ActivityDrawMessage> {

    private final RedisDeductionAwardNumberDrawExe drawExe;

    @Override
    public void onMessage(ActivityDrawMessage activityDrawMessage) {
        log.info("接受到MQ消息:{}", JSON.toJSONString(activityDrawMessage));
        String body = activityDrawMessage.getBody();
        ActivityDrawContext context = JSON.parseObject(body, ActivityDrawContext.class);
        Boolean result = drawExe.mqDeductionOfInventoryAndUpdateRecordStatus(context);
        if (Boolean.FALSE.equals(result)) {
            AssertUtil.isTrue(true, "执行消费MQ逻辑失败 ");
        }
    }
}
