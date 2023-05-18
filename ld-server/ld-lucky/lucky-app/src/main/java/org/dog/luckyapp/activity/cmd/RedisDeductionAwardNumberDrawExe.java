package org.dog.luckyapp.activity.cmd;

import lombok.extern.slf4j.Slf4j;
import org.dog.config.exception.LdException;
import org.dog.config.util.FileLoad;
import org.dog.luckyapp.context.ActivityDrawContext;
import org.dog.luckyapp.listener.AwardInventoryToRedisApplicationListener;
import org.dog.luckyapp.mq.product.ActivityDrawMessageProducer;
import org.dog.luckydomain.gateway.AwardGateway;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/16 08:18
 * @Description:
 */

@Slf4j
@Component
public class RedisDeductionAwardNumberDrawExe extends DefaultDrawExe {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ActivityDrawMessageProducer activityDrawMessageProducer;

    private static String stockDeductionLua;
    private static String stockRollbackLua;

    static {
        RedisDeductionAwardNumberDrawExe.stockDeductionLua = FileLoad.read("lua/stock_deduction.lua");
        RedisDeductionAwardNumberDrawExe.stockRollbackLua = FileLoad.read("lua/stock_rollback.lua");
    }

    public RedisDeductionAwardNumberDrawExe(
            AwardGateway awardGateway,
            PrizeGateway prizeGateway,
            TransactionTemplate transactionTemplate,
            RedisTemplate<String, Object> redisTemplate,
            ActivityDrawMessageProducer activityDrawMessageProducer
    ) {

        super(awardGateway, prizeGateway, transactionTemplate);
        this.redisTemplate = redisTemplate;
        this.activityDrawMessageProducer = activityDrawMessageProducer;
    }


    public Integer invokeStockDeductionLua(String key) {
        /**
         * stockDeductionLua:lua脚本
         * Long.class：返回执行后库存值的类型
         */
        RedisScript<Long> redisScript = new DefaultRedisScript<>(stockDeductionLua, Long.class);

        Long execute = redisTemplate.opsForValue().getOperations().execute(
                redisScript,
                List.of(key)
        );

        if (Objects.isNull(execute) || execute == -1) {
            return 0;
        }
        return 1;
    }

    public Integer invokeStockRollbackLua(String key) {
        /**
         * stockDeductionLua: lua 脚本
         * Long.class: 返回执行后的库存值类型
         */
        RedisScript<Long> redisScript = new DefaultRedisScript<>(stockRollbackLua, Long.class);

        Long execute = redisTemplate.opsForValue().getOperations().execute(
                redisScript,
                List.of(key));

        if (Objects.isNull(execute) || execute < 0) {
            return 0;
        }

        return 1;
    }

    @Override
    protected Boolean drawBefore(ActivityDrawContext context) {
        // 扣减Redis库存
        Integer deductionLua = invokeStockDeductionLua(AwardInventoryToRedisApplicationListener.getKey(
                context.getAwardEntity().getActivityId(), context.getAwardVO().getId()
        ));
        if (deductionLua != 1) {
            return Boolean.FALSE;
        }

        return super.getTransactionTemplate().execute(status -> {
            Boolean success = Boolean.TRUE;
            try {
                // 插入不可见抽奖记录
                super.addRecord(context);
                // 发送 MQ 消息
                if (Boolean.FALSE.equals(activityDrawMessageProducer.send(context)
                )) {
                    throw new LdException("MQ发送消息失败");
                }
            } catch (Exception e) {
                status.setRollbackOnly();
                invokeStockRollbackLua(AwardInventoryToRedisApplicationListener.getKey(
                        context.getAwardEntity().getActivityId(), context.getAwardVO().getId()
                ));
                success = Boolean.FALSE;
                log.error("扣减库存失败或发送MQ消息失败", e);
            }
            return success;
        });
    }

}
