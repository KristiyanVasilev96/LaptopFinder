package project.laptop.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //<- TYPE not field when using object
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {
    String first1();

    String second2();

    String message() default "Invalid Passwords";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
