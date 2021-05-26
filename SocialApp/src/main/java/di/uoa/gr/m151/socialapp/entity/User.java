package di.uoa.gr.m151.socialapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "social_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private Collection<UserActionLog> logList;

/*    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private Collection<FeedPost> feedPosts;*/

    @JsonIgnore
    @OneToMany(
            mappedBy = "sender",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Collection<Message> sentMessages;

    @JsonIgnore
    @OneToMany(
            mappedBy = "receiver",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Collection<Message> receivedMessages;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "social_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @ManyToMany(mappedBy = "upVotes")
    @JsonIgnore
    private Collection<ThreadPost> threadPosts;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<UserPageRating> pageRatings;

    public void addPageRating(Page page, Integer rating) {
        UserPageRating userPageRating = new UserPageRating(this, page, rating);
        pageRatings.add(userPageRating);
        //page.getUserRatings().add(userPageRating);
    }

    public void removePageRating(Page page) {
/*        userReactions.removeIf(userReaction -> userReaction.getUser().equals(user)
                && userReaction.getFeedPost().equals(this));*/
        for (Iterator<UserPageRating> iterator = pageRatings.iterator();
             iterator.hasNext(); ) {
            UserPageRating userPageRating = iterator.next();

            if (userPageRating.getUser().equals(this) &&
                    userPageRating.getPage().equals(page)) {
                iterator.remove();
                //userPageRating.getPage().getUserRatings().remove(userPageRating);
                userPageRating.setUser(null);
                userPageRating.setPage(null);
            }
        }
    }
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof User))
            return false;
        if (obj == this)
            return true;
        User user = ((User) obj);

        return this.getId() == user.getId() && this.getUsername() == user.getUsername();

    }

    @Override
    public int hashCode() {
        return this.getId().intValue();
    }

}