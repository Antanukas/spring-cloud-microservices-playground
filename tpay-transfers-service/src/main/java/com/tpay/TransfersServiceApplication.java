package com.tpay;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.security.HystrixSecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients = AccountsServiceClient.class)
//Strange that this one was needed to be imported explicitly
@ImportAutoConfiguration({ FeignAutoConfiguration.class })
@EnableHystrix
@EnableResourceServer
public class TransfersServiceApplication implements ResourceServerConfigurer {

	@Value("${authserver.secret}")
	private String authSecret;

	public static void main(String[] args) {
		SpringApplication.run(TransfersServiceApplication.class, args);
	}
	@Bean
	@RefreshScope
	CustomJwtTokenConverter converter() {
		CustomJwtTokenConverter c = new CustomJwtTokenConverter();
		c.setSigningKey(authSecret);
		return c;
	}

	//Needed to expose auth token for Feign client
	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

				requestTemplate.header("Authorization", "bearer " + details.getTokenValue());
			}
		};
	}

	//TODO refactor to commons
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

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
	}
}
