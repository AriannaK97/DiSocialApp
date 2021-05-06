package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
    Boolean existsByUsername(String username);

}
