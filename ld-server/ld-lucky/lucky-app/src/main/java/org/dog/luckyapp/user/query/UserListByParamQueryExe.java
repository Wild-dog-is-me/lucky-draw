package org.dog.luckyapp.user.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.luckyapp.assembler.UserAssembler;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckyclient.dto.query.UserListByParamQuery;
import org.dog.luckydomain.gateway.UserGateway;
import org.dog.luckydomain.user.UserEntity;
import org.springframework.stereotype.Component;

/**
 * @Author: Odin
 * @Date: 2023/5/4 15:44
 * @Description:
 */

@Slf4j
@Component
@AllArgsConstructor
public class UserListByParamQueryExe {

    private final UserGateway userGateway;

    public IPage<UserVO> execute(UserListByParamQuery query) {
        IPage<UserEntity> entityIPage = userGateway.listByParamQuery(query);
        return entityIPage.convert(UserAssembler::toUserVO);
    }
}
