package com.tpay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableResourceServer
public class AccountsServiceApplication implements AccountsServiceClient {

	@Value("${authserver.secret}")
	private String authSecret;

    @Override
	public String hi() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		return "Hi";
	}

	@Bean
	@RefreshScope
	CustomJwtTokenConverter converter() {
		CustomJwtTokenConverter c = new CustomJwtTokenConverter();
		c.setSigningKey(authSecret);
		return c;
	}

	@Bean
	ResourceServerTokenServices tokenServices(CustomJwtTokenConverter converter) {
		/*RemoteTokenServices ts = new RemoteTokenServices();
		ts.setAccessTokenConverter(new JwtAccessTokenConverter());
		ts.setCheckTokenEndpointUrl("http://localhost:9002/oauth/check_token");
		ts.setClientId("web-ui");
		ts.setClientSecret("");
		return ts;*/
		return new StatelessResourceServerTokenServices(converter);
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}
}
