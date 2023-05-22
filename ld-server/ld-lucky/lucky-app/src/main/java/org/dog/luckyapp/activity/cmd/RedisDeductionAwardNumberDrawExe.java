package org.dog.luckyapp.activity.cmd;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.enums.RecordStatusEnum;
import org.dog.config.exception.LdException;
import org.dog.config.util.AssertUtil;
import org.dog.config.util.FileLoad;
import org.dog.luckyapp.context.ActivityDrawContext;
import org.dog.luckyapp.listener.AwardInventoryToRedisApplicationListener;
import org.dog.luckyapp.mq.product.ActivityDrawMessageProducer;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;
import org.dog.luckydomain.gateway.AwardGateway;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.dog.luckydomain.gateway.RecordGateway;
import org.dog.luckydomain.record.RecordEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
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
            RecordGateway recordGateway,
            AwardGateway awardGateway,
            PrizeGateway prizeGateway,
            TransactionTemplate transactionTemplate,
            RedisTemplate<String, Object> redisTemplate,
            ActivityDrawMessageProducer activityDrawMessageProducer
    ) {

        super(recordGateway, awardGateway, prizeGateway, transactionTemplate);
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
                context.setIsShow(Boolean.FALSE);
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

    /**
     * 扣除库存并修改不可见中奖记录状态
     *
     * @param awardId
     * @return
     */
    public Boolean deductionOfInventoryAndUpdateRecordStatus(Long awardId, Long recordId) {
        return super.getTransactionTemplate().execute(status -> {
            Boolean success = Boolean.TRUE;

            try {
                // 扣减库存
                int update = super.getAwardGateway().deductionAwardNumber(awardId, 1);

                AssertUtil.isTrue(update != 1, "扣减库存失败！");

                // 修改不可见中奖记录状态
                Boolean updateStatus = super.getRecordGateway().updateStatus(recordId, RecordStatusEnum.STATUE_1.getValue());

                AssertUtil.isFalse(updateStatus, "修改记录状态失败！");
            } catch (Exception e) {
                //错误处理
                log.error("执行扣减库存或修改不可见中奖记录状态出错，", e);
                status.setRollbackOnly();
                success = Boolean.FALSE;
            }
            return success;
        });
    }

    /**
     * MQ执行：扣除库存并修改不可见中奖记录状态
     *
     * @param context
     * @return
     */
    public Boolean mqDeductionOfInventoryAndUpdateRecordStatus(ActivityDrawContext context) {
        return deductionOfInventoryAndUpdateRecordStatus(context.getAwardVO().getId(), context.getRecordId());
    }

    /**
     * 定时任务执行：扣除库存并修改不可见中奖记录状态
     * <p>
     * 定时扫描用户不可见状态的中奖记录，然后对比当前时间和数据创建时间，
     * 发现两者相隔 5 分钟，那么，定时任务就可以把这个记录查询出来，再来执行一边，方案三消费者流程
     */
    public void ScheduledExecuteDeductionOfInventoryAndUpdateRecordStatus() {
        LocalDateTime now = LocalDateTime.now().plusMinutes(-5);
        final var query = new RecordListByParamQuery();
        query.setStatus(RecordStatusEnum.STATUE_0.getValue());
        query.setPageSize(100);
        IPage<RecordEntity> page = super.getRecordGateway().page(query);
        for (RecordEntity record : page.getRecords()) {
            if (record.getCreateTime().isAfter(now)) {
                continue;
            }
            deductionOfInventoryAndUpdateRecordStatus(record.getAwardId(), record.getId());
        }
    }
}
