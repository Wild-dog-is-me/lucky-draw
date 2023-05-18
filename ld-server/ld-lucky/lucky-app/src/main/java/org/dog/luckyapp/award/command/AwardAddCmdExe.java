package org.dog.luckyapp.award.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.util.AssertUtil;
import org.dog.luckyapp.assembler.AwardAssembler;
import org.dog.luckyclient.dto.cmd.AwardAddCmd;
import org.dog.luckyclient.dto.data.AwardVO;
import org.dog.luckydomain.award.AwardEntity;
import org.dog.luckydomain.gateway.AwardGateway;
import org.dog.luckydomain.gateway.PrizeGateway;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/6 19:32
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class AwardAddCmdExe {

    private final AwardGateway awardGateway;

    private final PrizeGateway prizeGateway;
    public AwardVO execute(AwardAddCmd cmd) {
        AssertUtil.isTrue(Objects.isNull(cmd.getActivityId()), "奖项活动id不为空！");
        AwardEntity entity = awardGateway.save(AwardAssembler.toAddEntity(cmd));

        // 扣取奖品库存
        if (Boolean.FALSE.equals(entity.isPrize())) {
            // 代表该奖项是谢谢参与一类，不需要扣减奖品库存
            return AwardAssembler.toAwardVO(entity);
        }

        int update = prizeGateway.deductionInventory(cmd.getPrizeId(), cmd.getNumber());
        AssertUtil.isTrue(update < 1, String.format("扣取奖品：%s, 出错，库存不足或奖品不存在！", cmd.getPrizeId()));

        return AwardAssembler.toAwardVO(entity);
    }
}
