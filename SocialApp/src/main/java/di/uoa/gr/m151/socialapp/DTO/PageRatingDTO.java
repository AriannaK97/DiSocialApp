package di.uoa.gr.m151.socialapp.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class PageRatingDTO {

    String username;

    UUID pageId;

    String pageTitle;

    int rating;

}
