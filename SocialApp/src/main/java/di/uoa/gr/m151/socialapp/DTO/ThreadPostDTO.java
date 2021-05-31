package di.uoa.gr.m151.socialapp.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.UUID;

@Data
public class ThreadPostDTO {

        String content;

        String creatorUsername;

        String creatorColor;

        Boolean currentUserReaction;

        UUID threadId;

        Collection<String> upVotes;

        String postTime;

        @JsonIgnore
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");


}
