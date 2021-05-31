package di.uoa.gr.m151.socialapp.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class PageDTO {

    UUID pageId;

    String title;

    int currentUserRating;
}
