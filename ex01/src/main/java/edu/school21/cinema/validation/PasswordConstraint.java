package edu.school21.cinema.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Password  shall contain uppercase, lowercase letters, and at least one digit; field\n" +
            "length shall be at least 8 characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
