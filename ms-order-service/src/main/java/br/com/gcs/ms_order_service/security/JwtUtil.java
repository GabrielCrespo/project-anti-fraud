package br.com.gcs.ms_order_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String extractUsername(String header) {

        if (!StringUtils.hasLength(header) || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Token JWT ausente ou inválido");
        }

        String token = header.substring("Bearer ".length());

        Claims claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(token).getPayload();

        return claims.getSubject();


    }

}
