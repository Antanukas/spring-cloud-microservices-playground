package com.tpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class AccountsServiceApplication implements AccountsServiceClient {

    @Override
	public String hi() {
		return "Hi";
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}
}
