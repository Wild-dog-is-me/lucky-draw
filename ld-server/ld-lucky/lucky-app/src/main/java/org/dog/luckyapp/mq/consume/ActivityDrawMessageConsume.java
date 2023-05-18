package org.dog.luckyapp.mq.consume;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.dog.config.dto.ActivityDrawMessage;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/18 10:32
 * @Description:
 */

@Slf4j
@Component
@RocketMQMessageListener(topic = "activity-draw-sendTest-topic",consumerGroup = "lucky_draw")
public class ActivityDrawMessageConsume implements RocketMQListener<ActivityDrawMessage> {

    @Override
    public void onMessage(ActivityDrawMessage activityDrawMessage) {
        log.info("接受到MQ消息:{}", JSON.toJSONString(activityDrawMessage));
    }
}
