package org.dog.start.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableFeignClients(basePackages = "org.dog.luckyclient.feign")
@Configuration
@EnableScheduling
@EnableTransactionManagement
@ComponentScan("org.dog")
@MapperScan(basePackages = "org.dog.luckyinfrastructure.gateway.impl.mapper.*")
public class AppConfig {
}
