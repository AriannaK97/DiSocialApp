package di.uoa.gr.m151.socialapp.controller;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.DTO.FeedReactionDTO;
import di.uoa.gr.m151.socialapp.entity.FeedPost;
import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import di.uoa.gr.m151.socialapp.service.FeedReactionService;
import di.uoa.gr.m151.socialapp.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    FeedService feedService;

    @Autowired
    FeedReactionService feedReactionService;

    @PostMapping("/post")
    public FeedPost addPost(@RequestBody FeedPostDTO dto){return feedService.saveFeedPost(dto);}

    @GetMapping()
    public List<FeedPost> retrieveFeedPosts(){return feedService.findAllPosts();}

    @GetMapping("/newsfeed")
    public List<FeedPostDTO> getNewsFeedPosts(@RequestBody String username){return feedService.findAllPostsDTO(username);}

//    @PostMapping("/reaction")
//    public Set<FeedReaction> addUserReaction(@RequestBody FeedReactionDTO feedReactionDTO){
//        return feedReactionService.savePostReaction(feedReactionDTO);
//    }

    @PostMapping("/reaction")
    public void addUserReactionTest(@RequestBody FeedReactionDTO feedReactionDTO){
        feedReactionService.savePostReaction(feedReactionDTO);
    }

}
