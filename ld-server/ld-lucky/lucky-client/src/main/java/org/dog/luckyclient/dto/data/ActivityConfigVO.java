package org.dog.luckyclient.dto.data;

import lombok.Data;

import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/5/9 13:10
 * @Description:
 */

@Data
public class ActivityConfigVO {

    private ActivityVO activityVO;

    private List<RuleVO> ruleVOList;

    private List<AwardVO> awardVOList;
}
