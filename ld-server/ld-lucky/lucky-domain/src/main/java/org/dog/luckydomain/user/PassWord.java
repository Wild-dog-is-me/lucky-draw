package org.dog.luckydomain.user;

import cn.hutool.crypto.digest.MD5;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Odin
 * @Date: 2023/4/30 20:50
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
public class PassWord {

    private EncryptionPassWord encryptionPassWord;

//    public PassWord(EncryptionPassWord encryptionPassWord) {
//        this.encryptionPassWord = encryptionPassWord;
//    }

    public static String getEncryptionPassWord(String password) {
        return MD5.create().digestHex(password);
    }

    @Getter
    public static class EncryptionPassWord {

        private String password;

        public EncryptionPassWord(String password) {
            this.password = password;
        }
    }

    public Boolean isEqual(String password) {
        return this.encryptionPassWord.password.equals(getEncryptionPassWord(password));
    }

}
