package org.dog.common.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Odin
 * @Date: 2023/4/30 00:09
 * @Description:
 */
@Slf4j
@Configuration
@MapperScan("org.dog.luckyinfrastructure.gateway.impl.mapper")
public class MapperScanConfig {
}
