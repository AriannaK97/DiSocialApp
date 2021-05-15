package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.entity.ThreadPost;
import di.uoa.gr.m151.socialapp.entity.UserActionLog;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface UserActionLogRepository extends PagingAndSortingRepository<UserActionLog, UUID> {


}
