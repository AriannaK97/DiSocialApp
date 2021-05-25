package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.entity.FeedPost;
import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import di.uoa.gr.m151.socialapp.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface FeedReactionRepository extends PagingAndSortingRepository<FeedReaction, UUID> {
    Integer findFeedReactionByUser(User user);
}
