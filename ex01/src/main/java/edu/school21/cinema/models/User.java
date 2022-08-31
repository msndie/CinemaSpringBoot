package edu.school21.cinema.models;

import edu.school21.cinema.security.Role;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @NotBlank(message = "{first.name.notblack}")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @NotBlank(message = "{last.name.notblack}")
    private String lastName;

    @Column(name = "email", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "{email.notvalid}")
    private String email;

    @Column(name = "phone_number", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}