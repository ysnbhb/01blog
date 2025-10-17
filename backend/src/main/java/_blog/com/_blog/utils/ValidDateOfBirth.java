package _blog.com._blog.utils;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateOfBirth {
    String message() default "Invalid date of birth or must be at least 16 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println(value);
        if (value == null || value.isBlank()) {
            return false;
        }

        try {
            LocalDate dob = LocalDate.parse(value);
            LocalDate today = LocalDate.now();
            LocalDate minAgeDate = today.minusYears(16);

            return dob.isBefore(minAgeDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}