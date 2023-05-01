package org.dog.luckyclient.dto.query;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: Odin
 * @Date: 2023/4/30 21:37
 * @Description:
 */

@Data
@Accessors(chain = true)
public class UserListByParamQuery extends PageQuery {

    private Long id;

    private String username;

    private String password;

}
