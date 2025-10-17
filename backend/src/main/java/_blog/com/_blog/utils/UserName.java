package _blog.com._blog.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.Constraint;

@Documented
@Constraint(validatedBy = UserNameValid.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {
    String message() default "invalid username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class UserNameValid implements ConstraintValidator<UserName, String> {
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null || username.isEmpty()) {
            return true;
        }
        if (!username.matches(".*[A-Za-z].*") || username.length() < 3 || username.length() > 12)
            return false;
        return username.matches("^[A-Za-z0-9_]+$");
    }
}