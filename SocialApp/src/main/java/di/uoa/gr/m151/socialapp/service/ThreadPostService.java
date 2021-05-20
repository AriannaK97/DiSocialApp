package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.entity.ThreadPost;

import java.util.UUID;

public interface ThreadPostService {

    ThreadPost findById(UUID id);

    ThreadPost saveThreadPost(ThreadPost threadPost);

}
