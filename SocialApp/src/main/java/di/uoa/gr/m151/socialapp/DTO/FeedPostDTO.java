package di.uoa.gr.m151.socialapp.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import di.uoa.gr.m151.socialapp.entity.FeedReaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedPostDTO {
    UUID postId;

    String content;

    //Timestamp postTime;
    String postTime;

    @JsonIgnore
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    Collection<FeedReactionDTO> userReactions;

    String username;

    String userColor;

    String pageTitle;

    Integer score;

    Integer currentUserReaction;




}
