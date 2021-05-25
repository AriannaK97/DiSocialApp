package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.entity.FeedPost;
import di.uoa.gr.m151.socialapp.entity.FeedReaction;

import java.util.List;
import java.util.UUID;

public interface FeedService {
    FeedPost findById(UUID id);

    FeedPost saveFeedPost(FeedPostDTO feedPostDTO);

    List<FeedPost> findAllPosts();

    List<FeedPostDTO> findAllPostsDTO(String username);

}
