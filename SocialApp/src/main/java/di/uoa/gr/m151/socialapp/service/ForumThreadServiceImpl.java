package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.entity.ForumThread;
import di.uoa.gr.m151.socialapp.entity.ThreadPost;
import di.uoa.gr.m151.socialapp.repository.ForumThreadRepository;
import di.uoa.gr.m151.socialapp.repository.ThreadPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ForumThreadServiceImpl implements ForumThreadService{

    @Autowired
    ForumThreadRepository forumThreadRepository;

    @Override
    public ForumThread findById(UUID id) {

        return forumThreadRepository.findById(id).orElse(null);

    }

    @Override
    public ForumThread saveForumThread(ForumThread forumThread) {
        return  forumThreadRepository.save(forumThread);
    }
}
