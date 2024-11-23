package com.example.websocket.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {
    // Base64 encoded secret key
    private final String secretKeyString = "PGEyY2RjZmY5MzY2ZDEyODhiZWFmOTUwY2E5ZTMwNTI1Yzg2NTY="; // Example base64 for "my-secret-key"
    private final SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(secretKeyString), "HmacSHA256");
    private final long expiration = 86400000; // 1 day

    public String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey) // Use the secretKey
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey) // Use the secretKey
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey) // Use the secretKey
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Token is invalid
        }
        return false;
    }

    public Claims parseToken(String token) {
        String trimmedToken = token.trim(); // Remove leading/trailing spaces
        return Jwts.parser()
                .setSigningKey(secretKey) // Use the secretKey
                .parseClaimsJws(trimmedToken)
                .getBody();
    }
}