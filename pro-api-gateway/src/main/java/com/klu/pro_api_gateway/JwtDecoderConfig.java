package com.klu.pro_api_gateway;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtDecoderConfig {

    private static final String SECRET =
            "TalentSyncSecretKeyForJWTAuthentication2026VerySecureKey";

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {

        SecretKey key = Keys.hmacShaKeyFor(
                SECRET.getBytes(java.nio.charset.StandardCharsets.UTF_8)
        );

        return NimbusReactiveJwtDecoder
                .withSecretKey(key)
                .macAlgorithm(MacAlgorithm.HS384)
                .build();
    }
}