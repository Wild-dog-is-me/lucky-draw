package org.dog.config.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.CharEncoding;
import org.dog.config.exception.LdException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: Odin
 * @Date: 2023/5/4 14:46
 * @Description:
 */

@Slf4j
public class FileLoad {

    public static String read(String fileName) {
        String val = "";
        try {
            val = IoUtil.read(new FileInputStream(FileUtil.file(fileName)), CharEncoding.UTF_8);
        } catch (FileNotFoundException e) {
            throw new LdException(String.format("文件路径读取失败：%s"), fileName);
        }
        return val;
    }
}
