package learn.project.frm.domain;

import jakarta.persistence.*;
import lombok.Data;
import learn.project.frm.domain.User;

@Data
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tag;
    private String message;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User sender;

    public Topic(String tag, String message, User user) {
        this.tag = tag;
        this.message = message;
        this.sender = user;
    }

    public Topic() {
    }

    public String getSenderName()
    {
        return sender != null ? sender.getUsername() : "<none>";    }
}
