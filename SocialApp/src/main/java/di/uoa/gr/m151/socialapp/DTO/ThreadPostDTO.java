package di.uoa.gr.m151.socialapp.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ThreadPostDTO {

        String content;

        String creatorUsername;

        UUID threadId;
}
