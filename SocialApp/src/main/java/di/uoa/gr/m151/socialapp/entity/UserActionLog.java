package di.uoa.gr.m151.socialapp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "user_action_log")
@Data
public class UserActionLog {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    @Column(name = "USER_ACTION_LOG_ID", unique = true, nullable = false)
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "username")
    private String userName;

    @Column(name="action_time_stamp")
    private Timestamp actionTimeStamp;

    @Column(name="action", columnDefinition = "TEXT")
    private String userAction;

    @ManyToOne
    @JoinColumn(name = "social_user_id")
    private User user;

}