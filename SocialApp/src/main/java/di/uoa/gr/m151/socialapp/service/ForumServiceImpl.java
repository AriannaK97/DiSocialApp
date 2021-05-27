package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.ForumThreadDTO;
import di.uoa.gr.m151.socialapp.DTO.PageRatingDTO;
import di.uoa.gr.m151.socialapp.DTO.ThreadPostDTO;
import di.uoa.gr.m151.socialapp.DTO.ThreadVoteDTO;
import di.uoa.gr.m151.socialapp.entity.ForumThread;
import di.uoa.gr.m151.socialapp.entity.Page;
import di.uoa.gr.m151.socialapp.entity.ThreadPost;
import di.uoa.gr.m151.socialapp.entity.User;
import di.uoa.gr.m151.socialapp.repository.ForumThreadRepository;
import di.uoa.gr.m151.socialapp.repository.PageRepository;
import di.uoa.gr.m151.socialapp.repository.ThreadPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    ForumThreadRepository forumThreadRepository;

    @Autowired
    UserService userService;

    @Override
    public ForumThread findForumThreadById(UUID id) {

        return forumThreadRepository.findById(id).orElse(null);

    }

    @Override
    public ForumThread saveForumThread(ForumThreadDTO forumThreadDTO) {

        Page page = pageRepository.findById(forumThreadDTO.getPageId()).orElse(null);

        if (page == null) {
            return null;
        }

        ForumThread forumThread = new ForumThread();
        forumThread.setPage(page);
        forumThread.setTitle(forumThreadDTO.getTitle());

        return  forumThreadRepository.save(forumThread);
    }

    @Autowired
    ThreadPostRepository threadPostRepository;

    @Override
    public ThreadPost findThreadPostById(UUID id) {
        return threadPostRepository.findById(id).orElse(null);
    }

    @Override
    public ThreadPost saveThreadPost(ThreadPostDTO threadPostDTO) {

        ThreadPost threadPost = new ThreadPost();
        threadPost.setContent(threadPostDTO.getContent());

        User threadCreator = userService.findByUserName(threadPostDTO.getCreatorUsername());
        ForumThread thread = forumThreadRepository.findById(threadPostDTO.getThreadId()).orElse(null);

        if (threadCreator == null || thread == null) {
            return null;
        }

        threadPost.setCreator(threadCreator);
        threadPost.setThread(thread);
        threadPost.setTimestamp(new Timestamp(System.currentTimeMillis()));

        Page threadPostPage = thread.getPage();

        threadPostPage.setLastUpdated(threadPost.getTimestamp());
        pageRepository.save(threadPostPage);

        return  threadPostRepository.save(threadPost);
    }

    @Override
    public boolean saveThreadPostUpVote(ThreadVoteDTO threadVoteDTO) {
        ThreadPost threadPost = threadPostRepository.findById(threadVoteDTO.getThreadPostId()).orElse(null);
        User user = userService.findByUserName(threadVoteDTO.getUsername());
        if (threadPost == null || user == null) {
            return false;
        }

        threadPost.addUpVote(user);
        return threadPostRepository.save(threadPost) != null;

    }

    @Override
    public boolean savePageRating(PageRatingDTO pageRatingDTO) {

        Page page = pageRepository.findById(pageRatingDTO.getPageId()).orElse(null);
        User user = userService.findByUserName(pageRatingDTO.getUsername());

        if (page == null || user == null) {
            return false;
        }

        user.addPageRating(page, pageRatingDTO.getRating());
        return userService.update(user) != null;

    }

    @Autowired
    PageRepository pageRepository;

    @Override
    public Page findPageByTitle(String title) {
        return pageRepository.findByTitle(title).orElse(null);
    }

    @Override
    public Page findPageById(UUID id) {
        return pageRepository.findById(id).orElse(null);
    }

    @Override
    public Page savePage(Page page) {
        return pageRepository.save(page);
    }

    //TODO: pagination
    @Override
    public List<Page> findAllPages() {
       return pageRepository.findAll();
    }

    @Override
    public List<ForumThread> findAllThreadsByPage(UUID uuid) {
        return forumThreadRepository.findAllByPage_Id(uuid);
    }

    @Override
    public List<ThreadPost> findAllThreadsPostsByThread(UUID uuid) {
        return threadPostRepository.findAllByThread_Id(uuid);
    }


}
