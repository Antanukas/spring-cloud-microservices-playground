package com.tpay.resourceserver;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

class StatelessResourceServerTokenServices implements ResourceServerTokenServices {

    private final CustomJwtTokenConverter converter;

    public StatelessResourceServerTokenServices(CustomJwtTokenConverter converter) {
        this.converter = converter;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        OAuth2AccessToken token = converter.extractAccessToken(accessToken);
        return converter.extractAuthentication(token.getAdditionalInformation());
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        return converter.extractAccessToken(accessToken);
    }
}
