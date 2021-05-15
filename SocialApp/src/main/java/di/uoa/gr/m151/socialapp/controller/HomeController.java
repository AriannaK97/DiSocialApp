package di.uoa.gr.m151.socialapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import di.uoa.gr.m151.socialapp.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
/*@RequestMapping("/home")*/
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public ResponseEntity<?> showHomePage() {
        /* HttpHeaders responseHeaders = new HttpHeaders();*/
        return new ResponseEntity<String>(HttpStatus.CREATED.toString(),  new HttpHeaders(), HttpStatus.CREATED);
    }
}