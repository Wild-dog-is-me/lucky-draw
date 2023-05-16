package org.dog.luckyadapter.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.annotation.ResponseResult;
import org.dog.luckyapp.listener.event.ActivityCreateEvent;
import org.dog.luckyclient.dto.data.ActivityConfigVO;
import org.dog.luckyclient.dto.data.ActivityVO;
import org.dog.luckyclient.dto.data.AwardVO;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private final ApplicationEventMulticaster applicationMulticaster;

    @GetMapping("/testCreateEventTest")
    public void testCreateEventTest() {
        ActivityConfigVO activityConfigVO = new ActivityConfigVO();

        ActivityVO activityVO = new ActivityVO();
        activityVO.setId(11L);
        activityConfigVO.setActivityVO(activityVO);


        List<AwardVO> awardVOList = new ArrayList<>();
        AwardVO awardVO = new AwardVO();
        awardVO.setAwardName("测试奖项");
        awardVO.setId(35L);
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
        applicationMulticaster.multicastEvent(new ActivityCreateEvent("", activityConfigVO));

    }

}
