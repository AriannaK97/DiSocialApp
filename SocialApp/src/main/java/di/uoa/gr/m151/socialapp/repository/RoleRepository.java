package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    Role findByName(String role);

}
