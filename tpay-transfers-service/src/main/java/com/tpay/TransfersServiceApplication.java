package com.tpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients = AccountsServiceClient.class)
//Strange that this one was needed to be imported explicitly
@ImportAutoConfiguration(FeignAutoConfiguration.class)
public class TransfersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransfersServiceApplication.class, args);
	}
}
