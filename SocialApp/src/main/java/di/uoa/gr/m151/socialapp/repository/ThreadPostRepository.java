package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.entity.ThreadPost;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface ThreadPostRepository extends PagingAndSortingRepository<ThreadPost, UUID> {

    List<ThreadPost> findAllByThread_Id(UUID uuid);


}
