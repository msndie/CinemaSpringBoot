package edu.school21.cinema.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "users_sessions")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ip", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String ip;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "user_id")
    private Long userId;

    public String getFormatedDate() {
        return new SimpleDateFormat("MMMM dd, yyyy").format(dateTime);
    }

    public String getFormatedTime() {
        return new SimpleDateFormat("HH:mm").format(dateTime);
    }
}