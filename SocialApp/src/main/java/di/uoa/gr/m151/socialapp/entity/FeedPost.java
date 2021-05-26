package di.uoa.gr.m151.socialapp.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "feed_post")
public class FeedPost {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", unique = true, nullable = false)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "content")
    String content;

    @Column(name="post_time_stamp")
    private Timestamp postTime;

    @ManyToOne
    @JoinColumn(name = "social_user_id")
    private User user;

    @OneToMany(
            mappedBy = "feedPost",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Collection<FeedReaction> userReactions;

    public void addUserReaction(User user, Integer reactionType) {
        FeedReaction feedReaction = new FeedReaction(user, this, reactionType);
        userReactions.add(feedReaction);
    }

    public void removeUserReaction(User user) {

/*        userReactions.removeIf(userReaction -> userReaction.getUser().equals(user)
                && userReaction.getFeedPost().equals(this));*/

        for (Iterator<FeedReaction> iterator = userReactions.iterator();
             iterator.hasNext(); ) {
            FeedReaction feedReaction = iterator.next();

            if (feedReaction.getFeedPost().equals(this) &&
                    feedReaction.getUser().equals(user)) {
                iterator.remove();
                feedReaction.setUser(null);
                feedReaction.setFeedPost(null);
            }
        }
    }

}
