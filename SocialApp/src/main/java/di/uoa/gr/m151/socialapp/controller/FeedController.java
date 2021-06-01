package di.uoa.gr.m151.socialapp.controller;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.DTO.FeedReactionDTO;
import di.uoa.gr.m151.socialapp.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    FeedService feedService;


    @PostMapping("/post")
    @PreAuthorize("#dto.username == authentication.name")
    public FeedPostDTO addPost(@RequestBody FeedPostDTO dto){
        return feedService.saveFeedPost(dto);
    }


    @GetMapping("/newsfeed/{username}")
    @PreAuthorize("#username == authentication.name")
    public Collection<FeedPostDTO> showFeed(@PathVariable String username){

        return feedService.retrieveFeed(username);
    }


    @PostMapping("/reaction")
    @PreAuthorize("#feedReactionDTO.username == authentication.name")
    public boolean addUserReaction(@RequestBody FeedReactionDTO feedReactionDTO){
        return feedService.saveFeedPostReaction(feedReactionDTO);
    }

    @DeleteMapping("/reaction")
    @PreAuthorize("#username == authentication.name")
    public boolean removeUserReaction(@RequestParam UUID postId, @RequestParam String username) {
        System.out.println(postId + " " +  username);
        return feedService.removePostReaction(postId, username);
    }

}
