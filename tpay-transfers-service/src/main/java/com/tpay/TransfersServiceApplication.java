package com.tpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class TransfersServiceApplication {

    @Autowired
    RestTemplate restTemplate;

	@GetMapping
	public String fromAccounts() {
        String fromAccounts = restTemplate.getForObject("http://accounts", String.class);
		return fromAccounts;
	}

	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(TransfersServiceApplication.class, args);
	}
}
