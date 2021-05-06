package di.uoa.gr.m151.socialapp.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JWTConstants {

    private final String jwtSecret = "SecretKey";
    private final Long jwtExpirationMs = 900000L;
    private final String headerString = "Authorization";
    private final String tokenPrefix = "Bearer ";
    private final String loginUrl = "/api/auth/login";

}
