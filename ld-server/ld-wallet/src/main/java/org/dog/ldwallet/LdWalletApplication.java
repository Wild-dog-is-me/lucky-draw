package org.dog.ldwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LdWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdWalletApplication.class, args);
    }

}
