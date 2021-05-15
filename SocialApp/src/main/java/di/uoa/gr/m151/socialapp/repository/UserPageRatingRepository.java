package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.entity.UserPageRating;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface UserPageRatingRepository extends PagingAndSortingRepository<UserPageRating, UUID> {


}
