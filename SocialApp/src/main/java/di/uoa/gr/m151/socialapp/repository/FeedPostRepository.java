package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.DTO.FeedPostDTO;
import di.uoa.gr.m151.socialapp.entity.FeedPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.sql.Timestamp;

import java.util.List;
import java.util.UUID;

public interface FeedPostRepository extends PagingAndSortingRepository<FeedPost, UUID> {
    List<FeedPost> findAll();
}
