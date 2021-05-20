package di.uoa.gr.m151.socialapp.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "thread_post")
public class ThreadPost {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", unique = true, nullable = false)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "content")
    String content;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    User creator;

    @ManyToOne
    @JoinColumn(name = "thread_Id")
    ForumThread thread;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "thread_upvotes",
            joinColumns = @JoinColumn(name = "forum_thread_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Collection<User> upVotes;

    public void addUpVote(User user) {
        upVotes.add(user);
        user.getThreadPosts().add(this);
    }

    public void removeUpVote(User user) {
        upVotes.remove(user);
        user.getThreadPosts().remove(this);
    }

}
