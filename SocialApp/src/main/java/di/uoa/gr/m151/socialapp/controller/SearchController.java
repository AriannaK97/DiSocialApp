package di.uoa.gr.m151.socialapp.controller;

import di.uoa.gr.m151.socialapp.DTO.UserDTO;
import di.uoa.gr.m151.socialapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search/")
public class SearchController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<UserDTO> searchUsers(@RequestParam String keyword) {
        return userService.searchUsers(keyword);
    }

}
