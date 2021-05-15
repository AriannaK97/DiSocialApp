package di.uoa.gr.m151.socialapp.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "page")
@Data
public class Page {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "title")
    String title;

    @OneToMany(
            mappedBy = "page",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Collection<UserPageRating> userRatings;

    @OneToMany(mappedBy = "page",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Collection<ForumThread> threads;


}
