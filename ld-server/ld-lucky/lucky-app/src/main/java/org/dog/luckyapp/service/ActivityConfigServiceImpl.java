package org.dog.luckyapp.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.util.AssertUtil;
import org.dog.luckyapp.activity.cmd.ActivityAddCmdExe;
import org.dog.luckyapp.activity.cmd.ActivityUpdateCmdExe;
import org.dog.luckyapp.activity.query.ActivityListByParamQueryExe;
import org.dog.luckyapp.activityrule.cmd.ActivityRuleAddCmdExe;
import org.dog.luckyapp.activityrule.cmd.ActivityRuleDeleteExe;
import org.dog.luckyapp.activityrule.query.ActivityRuleListByParamQueryExe;
import org.dog.luckyapp.assembler.ActivityAssembler;
import org.dog.luckyapp.assembler.AwardAssembler;
import org.dog.luckyapp.award.command.AwardAddCmdExe;
import org.dog.luckyapp.award.command.AwardUpdateCmdExe;
import org.dog.luckyapp.award.query.AwardListByParamQueryExe;
import org.dog.luckyapp.listener.event.ActivityCreateEvent;
import org.dog.luckyapp.rule.query.RuleListByParamQueryExe;
import org.dog.luckyclient.api.IActivityConfigService;
import org.dog.luckyclient.dto.cmd.*;
import org.dog.luckyclient.dto.data.*;
import org.dog.luckyclient.dto.query.ActivityListByParamQuery;
import org.dog.luckyclient.dto.query.ActivityRuleListByParamQuery;
import org.dog.luckyclient.dto.query.AwardListByParamQuery;
import org.dog.luckyclient.dto.query.RuleListByParamQuery;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Odin
 * @Date: 2023/5/9 13:45
 * @Description:
 */

@Slf4j
@Service
@AllArgsConstructor
public class ActivityConfigServiceImpl implements IActivityConfigService {

    private final ActivityAddCmdExe activityAddCmdExe;
    private final ActivityRuleAddCmdExe activityRuleAddCmdExe;
    private final AwardAddCmdExe awardAddCmdExe;

    private final ActivityUpdateCmdExe activityUpdateCmdExe;
    private final ActivityRuleDeleteExe activityRuleDeleteExe;
    private final AwardUpdateCmdExe awardUpdateCmdExe;

    private final ActivityListByParamQueryExe activityListByParamQueryExe;
    private final RuleListByParamQueryExe ruleListByParamQueryExe;
    private final ActivityRuleListByParamQueryExe activityRuleListByParamQueryExe;
    private final AwardListByParamQueryExe awardListByParamQueryExe;

    private final ApplicationEventMulticaster applicationMulticaster;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActivityConfigVO add(ActivityConfigAddCmd cmd) {

        ActivityVO activityVO = activityAddCmdExe.execute(cmd.getActivityAddCmd());

        List<RuleVO> ruleVOList = addActivityRule(activityVO, cmd.getRuleIdList());

        List<AwardVO> awardVOList = addAward(activityVO, cmd.getAwardAddCmdList());

        ActivityConfigVO activityConfigVO = new ActivityConfigVO();
        activityConfigVO.setActivityVO(activityVO);
        activityConfigVO.setRuleVOList(ruleVOList);
        activityConfigVO.setAwardVOList(awardVOList);
        // 发送活动创建事件
        applicationMulticaster.multicastEvent(new ActivityCreateEvent("", activityConfigVO));

        return activityConfigVO;
    }

    private List<AwardVO> addAward(ActivityVO activityVO, List<AwardAddCmd> awardAddCmdList) {
        AssertUtil.isTrue(CollectionUtil.isEmpty(awardAddCmdList), "奖项不为空！");

        List<AwardVO> result = new ArrayList<>();
        for (AwardAddCmd awardAddCmd : awardAddCmdList) {
            awardAddCmd.setActivityId(activityVO.getId());
            result.add(awardAddCmdExe.execute(awardAddCmd));
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ActivityConfigVO update(ActivityConfigUpdateCmd cmd) {
        ActivityVO activityVO = activityUpdateCmdExe.execute(cmd.getActivityUpdateCmd());

        activityRuleDeleteExe.execute(activityVO.getId());
        List<RuleVO> ruleVOList = addActivityRule(activityVO, cmd.getRuleIdList());

        List<AwardVO> awardVOList = updateAward(activityVO, cmd.getAwardUpdateCmdList());

        ActivityConfigVO activityConfigVO = new ActivityConfigVO();
        activityConfigVO.setActivityVO(activityVO);
        activityConfigVO.setRuleVOList(ruleVOList);
        activityConfigVO.setAwardVOList(awardVOList);
        return activityConfigVO;
    }

    private List<AwardVO> updateAward(ActivityVO activityVO, List<AwardUpdateCmd> awardUpdateCmdList) {
        AssertUtil.isTrue(CollectionUtil.isEmpty(awardUpdateCmdList), "奖项不为空！");

        List<AwardVO> result = new ArrayList<>();
        for (AwardUpdateCmd awardUpdateCmd : awardUpdateCmdList) {
            result.add(awardUpdateCmdExe.execute(awardUpdateCmd));
        }

        return result;
    }


    @Override
    public ActivityConfigVO one(Long id) {
        final var activityListByParamQuery = new ActivityListByParamQuery();
        activityListByParamQuery.setId(id);
        List<ActivityVO> activityVOList = activityListByParamQueryExe.execute(activityListByParamQuery).getRecords();
        AssertUtil.isTrue(CollectionUtil.isEmpty(activityVOList), "数据不存在！");

        ActivityVO activityVO = activityVOList.get(0);

        final var activityRuleListByParamQuery = new ActivityRuleListByParamQuery();
        activityRuleListByParamQuery.setActivityId(activityVO.getId());
        List<ActivityRuleVO> activityRuleVOList = activityRuleListByParamQueryExe.execute(activityRuleListByParamQuery);
        List<RuleVO> ruleVOList = getRuleVOList(activityRuleVOList.stream().map(ActivityRuleVO::getRuleId).collect(Collectors.toList()));

        AwardListByParamQuery awardListByParamQuery = new AwardListByParamQuery();
        awardListByParamQuery.setActivityId(activityVO.getId());
        awardListByParamQuery.setPageSize(1000);
        List<AwardVO> awardVOList = awardListByParamQueryExe.execute(awardListByParamQuery).getRecords();


        ActivityConfigVO activityConfigVO = new ActivityConfigVO();
        activityConfigVO.setActivityVO(activityVO);
        activityConfigVO.setRuleVOList(ruleVOList);
        activityConfigVO.setAwardVOList(awardVOList);
        return activityConfigVO;
    }

    @Override
    public ActivityConfigCopyVO copy(Long id) {
        ActivityConfigCopyVO activityConfigCopyVO = new ActivityConfigCopyVO();

        ActivityConfigVO activityConfigVO = one(id);

        activityConfigCopyVO.setActivityAddCmd(ActivityAssembler.toActivityAddCmd(activityConfigVO.getActivityVO()));
        activityConfigCopyVO.setRuleIdList(activityConfigVO.getRuleVOList().stream().map(RuleVO::getId).collect(Collectors.toList()));
        activityConfigCopyVO.setAwardAddCmdList(
                new Page<AwardVO>().setRecords(activityConfigVO.getAwardVOList()).convert(AwardAssembler::toAwardAddCmd).getRecords()
        );

        return activityConfigCopyVO;
    }

    private List<RuleVO> addActivityRule(ActivityVO activityVO, List<Long> ruleIdList) {

        List<ActivityRuleAddCmd> cmdList = new ArrayList<>();
        for (Long ruleId : ruleIdList) {
            ActivityRuleAddCmd activityRuleAddCmd = new ActivityRuleAddCmd();
            activityRuleAddCmd.setActivityId(activityVO.getId());
            activityRuleAddCmd.setRuleId(ruleId);
            cmdList.add(activityRuleAddCmd);
        }
        List<ActivityRuleVO> activityRuleVOList = activityRuleAddCmdExe.execute(cmdList);

        return getRuleVOList(activityRuleVOList.stream().map(ActivityRuleVO::getRuleId).collect(Collectors.toList()));
    }


    private List<RuleVO> getRuleVOList(List<Long> ruleIdList) {
        RuleListByParamQuery query = new RuleListByParamQuery();
        query.setIds(ruleIdList);
        query.setPageSize(1000);

        return ruleListByParamQueryExe.execute(query).getRecords();
    }
}
