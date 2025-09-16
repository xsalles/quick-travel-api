package br.com.quick_travel.main.config.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JWTConfig {
    @Value("${jwt.secret}")
    private String secret;
}
