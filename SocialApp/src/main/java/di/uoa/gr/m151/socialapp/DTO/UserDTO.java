package di.uoa.gr.m151.socialapp.DTO;

import lombok.Data;


import java.util.Collection;

@Data
public class UserDTO {
    
    Long id;
    
    String username;
    
    String firstName;
    
    String lastName;
    
    String email;
    
    String phone;

    String color;
    
    Collection<PageRatingDTO> pageRatings;

    Collection<FeedPostDTO> userFeedPosts;

}
