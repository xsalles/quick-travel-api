package br.com.quick_travel.main.common.provider;

import br.com.quick_travel.main.config.jwt.JWTConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
public class JWTProvider {
    @Autowired
    private JWTConfig jwtConfig;

    public String generateToken(UUID id, String email) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());

        return JWT.create()
                .withExpiresAt(Instant.now().plus(Duration.ofHours(24)))
                .withIssuer("quick-travel")
                .withSubject(id.toString())
                .withClaim("email", email)
                .sign(algorithm);
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());

        token = token.replace("Bearer ", "");

        try {
            var subject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

            return subject;
        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
