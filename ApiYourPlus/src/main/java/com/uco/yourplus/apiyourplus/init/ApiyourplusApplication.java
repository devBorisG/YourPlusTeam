package com.uco.yourplus.apiyourplus.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.uco.yourplus")
@EnableJpaRepositories("com.uco.yourplus")
@EntityScan("com.uco.yourplus")
public class ApiyourplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiyourplusApplication.class, args);
    }

}
