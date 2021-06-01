package di.uoa.gr.m151.socialapp.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import di.uoa.gr.m151.socialapp.DTO.UserDTO;
import di.uoa.gr.m151.socialapp.config.JWTConstants;
import di.uoa.gr.m151.socialapp.config.JwtTokenResponse;
import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTConstants jwtProperties;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {

        authenticate(user.getUsername(), user.getPassword());

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getJwtExpirationMs()))
                .sign(Algorithm.HMAC512(jwtProperties.getJwtSecret().getBytes()));

        User responseUser = userService.findByUserName(user.getUsername());

        UserDTO userDTO = userService.fillEnhancedUserDTO(responseUser, false, false);


        return ResponseEntity.ok(new JwtTokenResponse(token, userDTO));


    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (userService.userExists(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username is taken");
        }

        if (user.getUsername() != null && !user.getUsername().isEmpty()
                && user.getPassword() != null && !user.getPassword().isEmpty()) {
            userService.save(user);
            return ResponseEntity.ok("User with username: " + user.getUsername() + " was created");

        }

        return ResponseEntity
                .badRequest()
                .body("Username or password are empty. Cannot create user");

    }


    @GetMapping("/profile/{username}")
    public ResponseEntity<?> userProfile(@PathVariable String username) {
        User user = userService.findByUserName(username);
        if (user != null) {
            UserDTO userDTO = userService.fillEnhancedUserDTO(user,false, true);
            return ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.badRequest().body("User does not exist");
    }


    private void authenticate(@NotNull String username, @NotNull String password) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

}