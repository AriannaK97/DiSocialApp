package di.uoa.gr.m151.socialapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "user_page_rating")
@Data
@NoArgsConstructor
public class UserPageRating {

    @EmbeddedId
    private UserPageRatingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pageId")
    private Page page;

    @Column(name = "rating")
    @Min(value = 0, message = "Rates are between 0 and 10")
    @Max(value = 10, message = "Rates are between 0 and 10")
    Integer rating;

    public UserPageRating(User user, Page page, Integer rating) {
        this.user = user;
        this.page = page;
        this.rating = rating;
        this.id = new UserPageRatingId(user.getId(), page.getId());
    }


}
