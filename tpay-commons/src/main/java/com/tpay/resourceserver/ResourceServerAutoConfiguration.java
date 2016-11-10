package com.tpay.resourceserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@ConditionalOnProperty(value = "tpay.autoconfigure.resourceserver", havingValue = "true")
@EnableResourceServer
public class ResourceServerAutoConfiguration implements ResourceServerConfigurer {

    @Value("${authserver.secret}")
    private String authSecret;

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

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/hystrix.stream").permitAll()
            .anyRequest().authenticated()
        ;
    }
}
