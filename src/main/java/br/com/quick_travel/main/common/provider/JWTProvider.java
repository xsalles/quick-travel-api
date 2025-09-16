package br.com.quick_travel.main.common.provider;

import br.com.quick_travel.main.config.jwt.JWTConfig;
import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.UUID;

public class JWTProvider {
    @Autowired
    private JWTConfig  jwtConfig;

    public String generateToken(UUID id, String email) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());

        return JWT.create()
                .withIssuer("quick-travel")
                .withSubject(id.toString())
                .withClaim("email", email)
                .sign(algorithm);
    }
}
