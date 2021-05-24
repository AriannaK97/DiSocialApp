package di.uoa.gr.m151.socialapp.controller;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.entity.FeedPost;
import di.uoa.gr.m151.socialapp.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    FeedService feedService;

    @PostMapping("/post")
    public FeedPost addPost(@RequestBody FeedPostDTO dto){return feedService.saveFeedPost(dto);}

    @GetMapping()
    public List<FeedPost> retrieveFeedPosts(){return feedService.findAllPosts();}

}
