package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.DTO.FeedReactionDTO;
import di.uoa.gr.m151.socialapp.entity.FeedPost;
import di.uoa.gr.m151.socialapp.entity.FeedReaction;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface FeedService {
    FeedPost findById(UUID id);

    FeedPostDTO saveFeedPost(FeedPostDTO feedPostDTO);

    List<FeedPost> findAllPosts();

    Collection<FeedPostDTO> retrieveFeed(String username);

    public boolean saveFeedPostReaction(FeedReactionDTO feedReactionDTO);

    public boolean removePostReaction(UUID feedPostId, String username );

}
