package org.dog.luckyapp.mq.product;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.dog.config.dto.ActivityDrawMessage;
import org.dog.luckyapp.context.ActivityDrawContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/17 20:05
 * @Description:
 */

@Slf4j
@Data
@Component
@AllArgsConstructor
public class ActivityDrawMessageProducer {

    private final RocketMQTemplate rocketMQTemplate;


    public Boolean send(ActivityDrawContext context) {
        final var activityDrawMessage = new ActivityDrawMessage()
                .setUuid(IdUtil.simpleUUID())
                .setBody(JSON.toJSONString(context));

        Message<ActivityDrawMessage> message = MessageBuilder
                .withPayload(activityDrawMessage)
                .build();

        SendResult sendResult = rocketMQTemplate.syncSend("activity-draw-topic", message);

        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            log.info("消息发送成功，业务ID：{}.uuid:{}",
                    activityDrawMessage.getId(), activityDrawMessage.getUuid());
            return Boolean.TRUE;
        }
        log.info("消息发送失败！");
        return Boolean.FALSE;
    }

    public Boolean sendTest(ActivityDrawContext context) {
        final var activityDrawMessage = new ActivityDrawMessage()
                .setUuid(IdUtil.simpleUUID())
                .setBody(JSON.toJSONString(context));

        Message<ActivityDrawMessage> message = MessageBuilder
                .withPayload(activityDrawMessage)
                .build();

        SendResult sendResult = rocketMQTemplate.syncSend("activity-draw-sendTest-topic", message);

        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            log.info("消息发送成功，业务ID：{}.uuid:{}",
                    activityDrawMessage.getId(), activityDrawMessage.getUuid());
            return Boolean.TRUE;
        }
        log.info("消息发送失败！");
        return Boolean.FALSE;
    }
}
