package di.uoa.gr.m151.socialapp.DTO;

import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class    FeedPostDTO {
    UUID postId;

    String content;

    //Timestamp postTime;
    Date postTime;

    Collection<FeedReactionDTO> userReactions;

    String username;

    String pageTitle;

    int score;

   // Integer currentUserReaction;




}
