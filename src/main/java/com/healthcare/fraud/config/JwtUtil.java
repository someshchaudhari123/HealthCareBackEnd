package com.healthcare.fraud.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // ⏳ Token validity
    @Value("${jwt.expiration}")
    private long expiration;

    // 🔐 Auto-generated secure key (created once)
    private final SecretKey signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ✅ Generate JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(signingKey)
                .compact();
    }

    // ✅ Extract username
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ Validate token
    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username)
                && !isTokenExpired(token);
    }

    // 🔍 Check expiration
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // 🔐 Parse claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
