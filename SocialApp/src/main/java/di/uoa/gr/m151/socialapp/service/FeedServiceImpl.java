package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.DTO.FeedReactionDTO;
import di.uoa.gr.m151.socialapp.entity.FeedPost;
import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.repository.FeedPostRepository;
import di.uoa.gr.m151.socialapp.repository.FeedReactionRepository;
import di.uoa.gr.m151.socialapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FeedServiceImpl implements FeedService{

    @Autowired
    FeedPostRepository feedPostRepository;

    @Autowired
    FeedReactionRepository feedReactionRepository;

    @Autowired
    UserService userService;

    @Override
    public FeedPost findById(UUID id) {
        return feedPostRepository.findById(id).orElse(null);
    }

    @Override
    public FeedPost saveFeedPost(FeedPostDTO feedPostDTO) {

        User postUser = userService.findByUserName(feedPostDTO.getUsername());

        FeedPost feedPost = new FeedPost();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        feedPost.setPostTime(timestamp);
        feedPost.setContent(feedPostDTO.getContent());
        feedPost.setUser(postUser);

        return feedPostRepository.save(feedPost);
    }

    @Override
    public List<FeedPost> findAllPosts() {
        return feedPostRepository.findAll();
    }

    @Override
    public List<FeedPostDTO> retrieveFeed(String username) {
        List<FeedPost> feedPostList = feedPostRepository.findAll();
        List<FeedPostDTO> feedList = new ArrayList<FeedPostDTO>();

        for (FeedPost feedPost : feedPostList) {
            FeedPostDTO dto = new FeedPostDTO();
            dto.setPostTime(feedPost.getPostTime());
            dto.setContent(feedPost.getContent());
            dto.setPostId(feedPost.getId());
            dto.setUsername(feedPost.getUser().getUsername());
            List<FeedReactionDTO> reactionList = new ArrayList<FeedReactionDTO>();
            for (FeedReaction feedReaction : feedPost.getUserReactions()) {
                FeedReactionDTO feedReactionDTO = new FeedReactionDTO();
                feedReactionDTO.setPostId(feedReaction.getFeedPost().getId());
                feedReactionDTO.setUsername(feedReaction.getUser().getUsername());
                feedReactionDTO.setReactionType(feedReaction.getReactionType());
                reactionList.add(feedReactionDTO);
            }
            dto.setUserReactions(reactionList);
            feedList.add(dto);
        }
        return feedList;
    }

    @Override
    public boolean saveFeedPostReaction(FeedReactionDTO feedReactionDTO){
        FeedPost feedPost = findById(feedReactionDTO.getPostId());
        User reactingUser = userService.findByUserName(feedReactionDTO.getUsername());

        if (feedPost == null || reactingUser == null) {
            return false;
        }
        /**
         * reaction '1' is the default reaction value for an existing reaction in the post
         * */
/*        if(feedReactionDTO.getReactionType() == 1)
            feedPost.addUserReaction(reactingUser, feedReactionDTO.getReactionType());
        else
            feedPost.removeUserReaction(reactingUser);*/
        feedPost.addUserReaction(reactingUser, feedReactionDTO.getReactionType());
        return  feedPostRepository.save(feedPost) != null;



        //return feedPost.getUserReactions();
    }

}
