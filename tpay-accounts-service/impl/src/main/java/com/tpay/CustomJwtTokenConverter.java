package com.tpay;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class CustomJwtTokenConverter extends JwtAccessTokenConverter {

    public OAuth2AccessToken extractAccessToken(String value) {
        return super.extractAccessToken(value, decode(value));
    }
}
