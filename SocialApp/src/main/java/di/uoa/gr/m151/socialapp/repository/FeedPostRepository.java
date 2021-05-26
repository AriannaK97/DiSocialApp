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



//    @Query("select new di.uoa.gr.m151.socialapp.DTO.FeedPostDTO(f.id, f.content, current_date , f.userReactions, f.user.username) " +
//            "from FeedPost as f inner join User su on f.user.id=su.id order by f.postTime")
//    List<FeedPostDTO> findFeedHistory();

/*    @Query("select new di.uoa.gr.m151.socialapp.DTO.FeedPostDTO(f.id, f.content, current_date , f.user.username) " +
            "from FeedPost as f  order by f.postTime")
    List<FeedPostDTO> findFeedHistory();*/

}
