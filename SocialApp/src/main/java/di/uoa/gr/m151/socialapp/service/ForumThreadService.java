package di.uoa.gr.m151.socialapp.service;


import di.uoa.gr.m151.socialapp.entity.ForumThread;

import java.util.UUID;

public interface ForumThreadService {

    ForumThread findById(UUID id);

    ForumThread saveForumThread(ForumThread thread);

}
