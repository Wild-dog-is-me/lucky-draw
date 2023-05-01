package org.dog.luckyapp.assembler;

import org.dog.luckyclient.dto.cmd.UserRegisterCmd;
import org.dog.luckyclient.dto.cmd.UserUpdateCmd;
import org.dog.luckyclient.dto.data.UserVO;
import org.dog.luckydomain.user.PassWord;
import org.dog.luckydomain.user.UserEntity;
import org.dog.luckydomain.user.UserName;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author: Odin
 * @Date: 2023/4/30 22:19
 * @Description:
 */


public class UserAssembler {
    public static UserEntity toAddEntity(UserRegisterCmd cmd) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(new UserName(cmd.getUsername()));
        userEntity.setPassword(new PassWord(new PassWord.EncryptionPassWord(PassWord.getEncryptionPassWord(cmd.getPassword()))));
        userEntity.setName(cmd.getName());
        userEntity.setPhone(cmd.getPhone());
        userEntity.setCreateTime(LocalDateTime.now());
        userEntity.setUpdateTime(LocalDateTime.now());

        return userEntity;
    }

    public static UserVO toUserVO(UserEntity entity) {
        UserVO userVO = new UserVO();
        userVO.setId(entity.getId());
        userVO.setUsername(entity.getUsername().getUsername());
        userVO.setName(entity.getName());
        userVO.setPhone(entity.getPhone());
        userVO.setCreateTime(entity.getCreateTime());
        userVO.setUpdateTime(entity.getUpdateTime());

        return userVO;
    }

    public static UserEntity toUpdateEntity(UserUpdateCmd cmd) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(cmd.getId());
        userEntity.setUsername(new UserName(cmd.getUsername()));
        if (Objects.nonNull(cmd.getPassword())){
            userEntity.setPassword(new PassWord(new PassWord.EncryptionPassWord(PassWord.getEncryptionPassWord(cmd.getPassword()))));
        }
        userEntity.setName(cmd.getName());
        userEntity.setPhone(cmd.getPhone());
        userEntity.setUpdateTime(LocalDateTime.now());

        return userEntity;
    }
}
