package com.uco.yourplus.apiyourplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.uco.yourplus")
public class ApiyourplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiyourplusApplication.class, args);
	}

}
