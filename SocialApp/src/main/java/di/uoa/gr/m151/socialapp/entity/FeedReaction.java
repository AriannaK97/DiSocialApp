package di.uoa.gr.m151.socialapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "feed_reaction")
@Data
@NoArgsConstructor
public class FeedReaction {

    @EmbeddedId
    private FeedReactionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("feedPostId")
    private FeedPost feedPost;

    @Column(name = "reaction_type")
    @Min(value = 0, message = "Types are between 0 and 5")
    @Max(value = 5, message = "Types are between 0 and 5")
    private Integer reactionType;

    public FeedReaction(User user, FeedPost feedPost, Integer reactionType) {
        this.user = user;
        this.feedPost = feedPost;
        this.reactionType = reactionType;
        this.id = new FeedReactionId(user.getId(), feedPost.getId());
    }


}
