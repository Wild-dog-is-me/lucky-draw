package org.dog.luckyclient.api;

import org.dog.luckyclient.dto.cmd.AcceptPrizeAddCmd;
import org.dog.luckyclient.dto.data.AcceptPrizeVO;

/**
 * @Author: Odin
 * @Date: 2023/5/25 23:56
 * @Description:
 */
public interface IAcceptPrizeService {

    AcceptPrizeVO one(Long recordId);

    AcceptPrizeVO add(AcceptPrizeAddCmd cmd);
}
