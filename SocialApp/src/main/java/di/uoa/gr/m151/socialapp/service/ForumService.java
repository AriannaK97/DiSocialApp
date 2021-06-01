package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.*;
import di.uoa.gr.m151.socialapp.entity.ForumThread;
import di.uoa.gr.m151.socialapp.entity.Page;
import di.uoa.gr.m151.socialapp.entity.ThreadPost;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ForumService {

    ForumThread findForumThreadById(UUID id);

    ForumThread saveForumThread(ForumThreadDTO forumThreadDTO);

    ThreadPost findThreadPostById(UUID id);

    ThreadPost saveThreadPost(ThreadPostDTO threadPostDTO);

    boolean saveThreadPostUpVote(ThreadVoteDTO threadVoteDTO);

    boolean removeThreadPostUpVote(UUID threadPostId, String username);

    boolean savePageRating(PageRatingDTO pageRatingDTO);

    Page findPageByTitle(String title);

    Page findPageById(UUID id);

    Page savePage(Page page);

    List<PageDTO> findAllPages(String currentUsername);

    List<ForumThread> findAllThreadsByPage(UUID uuid);

    List<ThreadPostDTO> findAllThreadsPostsByThread(UUID uuid, String currentUsername, Integer page);


}
