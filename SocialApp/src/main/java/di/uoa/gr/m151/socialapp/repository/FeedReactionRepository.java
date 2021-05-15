package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface FeedReactionRepository extends PagingAndSortingRepository<FeedReaction, UUID> {


}
