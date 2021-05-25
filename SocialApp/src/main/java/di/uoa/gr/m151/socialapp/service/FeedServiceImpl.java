package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.entity.FeedPost;
import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.repository.FeedPostRepository;
import di.uoa.gr.m151.socialapp.repository.FeedReactionRepository;
import di.uoa.gr.m151.socialapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public List<FeedPostDTO> findAllPostsDTO() {
        //todo fix return with dto
        //return feedPostRepository.findFeedHistory();
        return null;
    }

}
