package di.uoa.gr.m151.socialapp.controller;

import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    UserService userService;

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
}