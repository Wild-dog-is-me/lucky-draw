package org.dog.start;

import org.dog.config.util.FileLoad;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("org.dog")
@MapperScan(basePackages = "org.dog.luckyinfrastructure.gateway.impl.mapper")
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        System.out.println(FileLoad.read("lua/stock_rollback.lua"));
    }

}
