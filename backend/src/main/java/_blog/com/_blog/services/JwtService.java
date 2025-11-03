package _blog.com._blog.services;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import _blog.com._blog.Entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(30);
    @Value("${jwt.token}")
    private String secretKey;

    private Key getSigningKey() {
        System.out.println(secretKey);
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("uuid", user.getUuid());

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(user.getId()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Long extractUserId(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Long.parseLong(claims.getSubject());
        } catch (JwtException | NumberFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid or expired JWT token", e);
        }
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid or expired JWT token", e);
        }
    }

    public boolean isTokenValid(String token, UserEntity user) {
        try {
            Long id = extractUserId(token);
            Claims claims = extractAllClaims(token);
            return id.equals(user.getId()) && !claims.getExpiration().before(new Date());
        } catch (RuntimeException e) {
            return false;
        }
    }
}
