package di.uoa.gr.m151.socialapp.repository;

import di.uoa.gr.m151.socialapp.entity.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PageRepository extends PagingAndSortingRepository<Page, UUID> {

    Optional<Page> findByTitle(String title);

    List<Page> findAll();


}
