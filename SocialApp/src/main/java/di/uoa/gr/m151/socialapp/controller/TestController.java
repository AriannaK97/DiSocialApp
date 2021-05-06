package di.uoa.gr.m151.socialapp.controller;

import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests/")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {

        return userService.findAllUsers();

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
/*        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }*/




        userService.save(user);

        return ResponseEntity.ok("User Created");

    }
}
