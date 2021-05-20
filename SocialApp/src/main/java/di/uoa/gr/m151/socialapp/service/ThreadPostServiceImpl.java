package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.entity.ThreadPost;
import di.uoa.gr.m151.socialapp.repository.ThreadPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ThreadPostServiceImpl implements ThreadPostService{

    @Autowired
    ThreadPostRepository threadPostRepository;

    @Override
    public ThreadPost findById(UUID id) {
        return threadPostRepository.findById(id).orElse(null);
    }

    @Override
    public ThreadPost saveThreadPost(ThreadPost threadPost) {
        return  threadPostRepository.save(threadPost);
    }
}
