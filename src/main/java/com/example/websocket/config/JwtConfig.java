package com.example.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {

    // Replace this with your actual Base64 encoded secret key
    private final String secretKeyString = "PGEyY2RjZmY5MzY2ZDEyODhiZWFmOTUwY2E5ZTMwNTI1Yzg2NTY="; // Example base64 for "my-secret-key"

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(secretKeyString), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}
