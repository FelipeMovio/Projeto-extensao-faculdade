package com.sistema_docao.demo.config;

import com.sistema_docao.demo.entity.Usuario;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {
    private String secret = "secret";

    public String generateToken(Usuario user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("userId", user.getId())
                .withClaim("roles", user.getRoles().stream().map(Enum::name).toList())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400)) // 24h duração do token
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decoded = JWT.require(algorithm).build().verify(token);

            return Optional.of(JWTUserData.builder()
                    .userId(decoded.getClaim("userId").asLong())
                    .email(decoded.getSubject())
                    .roles(decoded.getClaim("roles").asList(String.class))
                    .build());
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
