package di.uoa.gr.m151.socialapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import di.uoa.gr.m151.socialapp.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/")
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> showHomePage() {
        return (ResponseEntity<?>) ResponseEntity.status(200);
    }
}
