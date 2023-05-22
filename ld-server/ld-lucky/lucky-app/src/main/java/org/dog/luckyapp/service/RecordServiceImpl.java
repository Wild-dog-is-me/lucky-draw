package org.dog.luckyapp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.config.util.AssertUtil;
import org.dog.luckyapp.record.command.RecordAddCmdExe;
import org.dog.luckyapp.record.command.RecordUpdateStatusCmdExe;
import org.dog.luckyapp.record.query.RecordListByParamQueryExe;
import org.dog.luckyapp.record.query.RecordMoneyParamQueryExe;
import org.dog.luckyclient.api.IRecordService;
import org.dog.luckyclient.dto.cmd.RecordAddCmd;
import org.dog.luckyclient.dto.cmd.RecordUpdateStatusCmd;
import org.dog.luckyclient.dto.data.RecordVO;
import org.dog.luckyclient.dto.query.RecordListByParamQuery;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/5/21 17:41
 * @Description:
 */

@Slf4j
@Service
@AllArgsConstructor
public class RecordServiceImpl implements IRecordService {

    private final RecordAddCmdExe recordAddCmdExe;
    private final RecordUpdateStatusCmdExe recordUpdateStatusCmdExe;
    private final RecordListByParamQueryExe recordListByParamQueryExe;
    private final RecordMoneyParamQueryExe recordMoneyParamQueryExe;



    @Override
    public IPage<RecordVO> page(RecordListByParamQuery query) {
        return recordListByParamQueryExe.execute(query);
    }

    @Override
    public RecordVO add(RecordAddCmd cmd) {
        return recordAddCmdExe.execute(cmd);
    }

    @Override
    public Boolean update(RecordUpdateStatusCmd cmd) {
        return recordUpdateStatusCmdExe.execute(cmd);
    }

    @Override
    public Integer prizeType(Long recordId) {
        return getPrizeByRecordId(recordId).getPrizeType();
    }

    public RecordVO getPrizeByRecordId(Long recordId) {
        final var recordQuery = new RecordListByParamQuery();
        recordQuery.setRecordId(recordId);

        List<RecordVO> recordVOList = recordListByParamQueryExe.execute(recordQuery).getRecords();
        AssertUtil.isTrue(CollUtil.isEmpty(recordVOList) || Objects.isNull(recordVOList.get(0)), "数据不存在！");

        return recordVOList.get(0);
    }


    @Override
    public Boolean exchangeMoney(Long recordId) {

        RecordVO recordVO = getPrizeByRecordId(recordId);
        AssertUtil.isTrue(recordVO.getPrizeType() != 2, "奖品类型兑换出错！");

        AssertUtil.isTrue(recordVO.getState() != 1, "记录状态非法！");

        // 获取奖品金额
        BigDecimal money = recordMoneyParamQueryExe.execute(recordId);

        // 将记录状态改为，4
        final var statusCmd = new RecordUpdateStatusCmd();
        statusCmd.setId(recordId);
        statusCmd.setState(4);
        update(statusCmd);

//        try {
//            // TODO: 调用给用户钱包价钱逻辑
//            final var walletForm = new UpdateWalletForm();
//            walletForm.setUpdateMoney(money);
//            walletForm.setUserId(SecurityUtil.getUserId());
//            WalletUpdateResultVO walletUpdateResultVO = walletFeignApi.updateBalance(walletForm);
//
//            if (Boolean.FALSE.equals(walletUpdateResultVO.getResult())) {
//                return Boolean.FALSE;
//            }
//        } catch (Exception e) {
//            //错误处理
//            log.error("调用修改用户钱包金额失败：", e);
//
//            // 回滚记录状态
//            statusCmd.setState(1);
//            update(statusCmd);
//
//            return Boolean.FALSE;
//        }

        return Boolean.TRUE;
    }
}
