package edu.school21.cinema.models;

import edu.school21.cinema.security.Role;
import edu.school21.cinema.validation.PasswordConstraint;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users", schema = "ex02_boot")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @NotBlank(message = "{first.name.notblank}")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @NotBlank(message = "{last.name.notblank}")
    private String lastName;

    @Column(name = "email", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "{email.invalid}")
    private String email;

    @Column(name = "phone_number", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @Pattern(regexp = "^(\\+7|7|8)?[\\s\\-]?\\(?[\\d][0-9]{2}\\)?[\\s\\-]?[0-9]{3}[\\s\\-]?[0-9]{2}[\\s\\-]?[0-9]{2}$",
            message = "{phone.invalid}")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @PasswordConstraint(message = "{password.invalid}")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "unique_code")
    private UUID uniqueCode;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}