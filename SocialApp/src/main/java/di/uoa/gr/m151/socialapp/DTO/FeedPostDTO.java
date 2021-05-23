package di.uoa.gr.m151.socialapp.DTO;

import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
public class FeedPostDTO {
    UUID postId;

    String content;

    Timestamp postTime;

    Set<FeedReaction> userReactions;

    String username;

}
