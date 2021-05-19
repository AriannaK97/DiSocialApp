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

}
