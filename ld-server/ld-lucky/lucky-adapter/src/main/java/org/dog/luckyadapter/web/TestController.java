package org.dog.luckyadapter.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyapp.activity.cmd.RedisDeductionAwardNumberDrawExe;
import org.dog.luckyapp.context.ActivityDrawContext;
import org.dog.luckyapp.listener.AwardInventoryToRedisApplicationListener;
import org.dog.luckyapp.listener.event.ActivityCreateEvent;
import org.dog.luckyapp.mq.producer.ActivityDrawMessageProducer;
import org.dog.luckyclient.api.IRecordService;
import org.dog.luckyclient.dto.data.ActivityConfigVO;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckyclient.dto.data.AwardVO;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/15 21:36
 * @Description:
 */

@Slf4j
@AllArgsConstructor
@ResponseResult
@RequestMapping("/v1/test")
public class TestController {

    private final ApplicationEventMulticaster applicationEventMulticaster;

    private final RedisDeductionAwardNumberDrawExe drawExe;

    private final ActivityDrawMessageProducer activityDrawMessageProducer;

    private final IRecordService recordService;

    @GetMapping("/activityCreateEventTest")
    public void activityCreateEventTest() {

        ActivityConfigVO activityConfigVO = new ActivityConfigVO();

        ActivityVO activityVO = new ActivityVO();
        activityVO.setId(1L);
        activityConfigVO.setActivityVO(activityVO);


        List<AwardVO> awardVOList = new ArrayList<>();
        AwardVO awardVO = new AwardVO();
        awardVO.setAwardName("测试奖项");
        awardVO.setId(100L);
        awardVO.setPrizeId(1L);
        awardVO.setNumber(200);
        awardVOList.add(awardVO);


        AwardVO awardVO2 = new AwardVO();
        awardVO2.setAwardName("测试奖项");
        awardVO2.setId(200L);
        awardVO2.setPrizeId(1L);
        awardVO2.setNumber(400);
        awardVOList.add(awardVO2);
        activityConfigVO.setAwardVOList(awardVOList);
        // 发送活动创建事件
        applicationEventMulticaster.multicastEvent(new ActivityCreateEvent("", activityConfigVO));
    }


    @GetMapping("/invokeStockDeductionLuaTest")
    public Integer invokeStockDeductionLuaTest() {
        return drawExe.invokeStockDeductionLua(
                AwardInventoryToRedisApplicationListener.getKey(1L, 100L));
    }

    @GetMapping("/invokeStockRollbackLuaTest")
    public Integer invokeStockRollbackLuaTest() {
        return drawExe.invokeStockRollbackLua(
                AwardInventoryToRedisApplicationListener.getKey(1L, 100L));
    }

    @GetMapping("/activityDrawMessageProducerTest")
    public Boolean activityDrawMessageProducerTest() {
        return activityDrawMessageProducer.sendTest(new ActivityDrawContext());
    }

    @GetMapping("/exchangeMoneyTest")
    public Boolean exchangeMoneyTest(@RequestParam("recordId") Long recordId) {
        return recordService.exchangeMoney(recordId);
    }

}
