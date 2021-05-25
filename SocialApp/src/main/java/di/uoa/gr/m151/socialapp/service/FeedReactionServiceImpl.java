package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.FeedReactionDTO;
import di.uoa.gr.m151.socialapp.entity.FeedPost;
import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.repository.FeedPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FeedReactionServiceImpl implements FeedReactionService{

    @Autowired
    FeedService feedService;

    @Autowired
    UserService userService;

    @Autowired
    FeedPostRepository feedPostRepository;

    @Override
    public void savePostReaction(FeedReactionDTO feedReactionDTO){
        FeedPost feedPost = feedService.findById(feedReactionDTO.getPostId());
        User reactingUser = userService.findByUserName(feedReactionDTO.getUsername());
        /**
         * reaction '1' is the default reaction value for an existing reaction in the post
         * */
        if(feedReactionDTO.getReactionType() == 1)
            feedPost.addUserReaction(reactingUser, feedReactionDTO.getReactionType());
        else
            feedPost.removeUserReaction(reactingUser);
        feedPostRepository.save(feedPost);

        //return feedPost.getUserReactions();
    }
}
