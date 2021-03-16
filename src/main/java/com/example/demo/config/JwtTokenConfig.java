package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * 
 * Helper that translates between JWT encoded token values and OAuth authentication
 * information (in both directions). Also acts as a {@link TokenEnhancer} when tokens are
 * granted.
 *
 */

@Configuration
public class JwtTokenConfig {

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        /**
    	 * Sets the JWT signing key. It can be either a simple MAC key or an RSA key. RSA keys
    	 * should be in OpenSSH format, as produced by <tt>ssh-keygen</tt>.
    	 *
    	 * @param key the key to be used for signing JWTs.
    	 */
        accessTokenConverter.setSigningKey("dev");
        return accessTokenConverter;
    }

    @Bean
    public TokenEnhancer jwtTokenEnhancer(){
       return new JWTokenEnhancer();
    }
}