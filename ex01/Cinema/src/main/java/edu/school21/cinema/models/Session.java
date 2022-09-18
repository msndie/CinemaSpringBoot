package edu.school21.cinema.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import edu.school21.cinema.utils.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "sessions", schema = "ex01_boot")
public class Session implements Serializable {

    @JsonView(View.Search.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @JsonView(View.Search.class)
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    private BigDecimal price;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @JsonView(View.Search.class)
    @JsonProperty("dateTime")
    public String getFormattedDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
