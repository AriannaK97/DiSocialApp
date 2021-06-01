package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
    Page<User> findAll(Pageable page);
    Boolean existsByUsername(String username);
    List <User> findByUsernameContaining(String usernamePart);

}
