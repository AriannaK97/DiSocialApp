package di.uoa.gr.m151.socialapp.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import di.uoa.gr.m151.socialapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    private JWTConstants jwtProperties;

    private UserService userService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTConstants jwtProperties,UserService userService) {
        super(authManager);
        this.jwtProperties = jwtProperties;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(jwtProperties.getHeaderString());

        if (header == null || !header.startsWith(jwtProperties.getTokenPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeaderString());

        if (token != null) {
            try {
                String user = JWT.require(Algorithm.HMAC512(jwtProperties.getJwtSecret().getBytes()))
                    .build()
                    .verify(token.replace(jwtProperties.getTokenPrefix(), ""))
                    .getSubject();
                if (user != null) {
                    UserDetails userDetails = userService.loadUserByUsername(user);
                    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                }
            } catch (JWTDecodeException e) {
                logger.error("Cannot decode JWT token: {}",e.getMessage());
            }
            catch (TokenExpiredException e) {
                logger.error("JWT token is expired: {}", e.getMessage());
            }
            catch (Exception exception) {
                logger.error("Generic error: {}", exception.getMessage());
            }

            return null;
        }
        return null;
    }
}

