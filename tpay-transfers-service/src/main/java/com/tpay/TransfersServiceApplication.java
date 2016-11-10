package com.tpay;

import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients = AccountsServiceClient.class)
//Strange that this one was needed to be imported explicitly
@ImportAutoConfiguration({ FeignAutoConfiguration.class })
@EnableHystrix
public class TransfersServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(TransfersServiceApplication.class, args);
	}

	//Needed to expose auth token for Feign client
	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
		return requestTemplate -> {
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

            requestTemplate.header("Authorization", "bearer " + details.getTokenValue());
        };
	}
}
