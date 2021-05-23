package di.uoa.gr.m151.socialapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "forum_thread")
public class ForumThread {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", unique = true, nullable = false)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "page_id")
    private Page page;

    @OneToMany(mappedBy = "thread",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Collection<ThreadPost> posts;



}
