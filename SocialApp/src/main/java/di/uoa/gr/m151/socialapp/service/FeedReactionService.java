package di.uoa.gr.m151.socialapp.service;

import di.uoa.gr.m151.socialapp.DTO.FeedReactionDTO;
import di.uoa.gr.m151.socialapp.entity.FeedReaction;

import java.util.Set;
import java.util.UUID;

public interface FeedReactionService {
    //public Set<FeedReaction> savePostReaction(FeedReactionDTO feedReactionDTO);
    public void savePostReaction(FeedReactionDTO feedReactionDTO);
}
