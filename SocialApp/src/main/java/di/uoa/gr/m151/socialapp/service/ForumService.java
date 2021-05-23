package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.ForumThreadDTO;
import di.uoa.gr.m151.socialapp.DTO.ThreadPostDTO;
import di.uoa.gr.m151.socialapp.entity.ForumThread;
import di.uoa.gr.m151.socialapp.entity.Page;
import di.uoa.gr.m151.socialapp.entity.ThreadPost;

import java.util.List;
import java.util.UUID;

public interface ForumService {

    ForumThread findForumThreadById(UUID id);

    ForumThread saveForumThread(ForumThreadDTO forumThreadDTO);

    ThreadPost findThreadPostById(UUID id);

    ThreadPost saveThreadPost(ThreadPostDTO threadPostDTO);

    Page findPageByTitle(String title);

    Page findPageById(UUID id);

    Page savePage(Page page);

    List<Page> findAllPages();

    List<ForumThread> findAllThreadsByPage(UUID uuid);

    List<ThreadPost> findAllThreadsPostsByThread(UUID uuid);

    /*List<ForumThread> findAllPageThreads()*/

}
