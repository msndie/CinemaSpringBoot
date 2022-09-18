package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "users_sessions", schema = "ex01_boot")
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

    public String getFormattedDate() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
    }

    public String getFormattedTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}