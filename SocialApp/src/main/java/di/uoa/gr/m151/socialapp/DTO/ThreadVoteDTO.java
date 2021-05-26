package di.uoa.gr.m151.socialapp.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ThreadVoteDTO {

    UUID threadPostId;

    String username;

}
