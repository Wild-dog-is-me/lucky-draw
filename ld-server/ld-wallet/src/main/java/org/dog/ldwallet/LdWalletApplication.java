package org.dog.ldwallet;

import org.dog.ldwallet.service.WalletService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

@EnableScheduling
@SpringBootApplication
public class LdWalletApplication {

    public static void main(String[] args) {

        SpringApplication.run(LdWalletApplication.class, args);
    }

}
