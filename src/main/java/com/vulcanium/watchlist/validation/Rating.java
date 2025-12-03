package com.vulcanium.watchlist.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // To indicate that the custom annotation should be used on instance variables (fields)
@Retention(RetentionPolicy.RUNTIME) // To indicate that the custom annotation should be effective at runtime
@Constraint(validatedBy = RatingValidator.class) // To specify that the logic of this custom validation annotation is implemented in the RatingValidator class
public @interface Rating {

    String message() default "Rating should be a number between 1 and 10";

    // To specify by default to Spring so that it recognizes it as a custom annotation
    Class<?>[] groups() default {};

    // To specify by default to Spring so that it recognizes it as a custom annotation
    Class<? extends Payload>[] payload() default {};
}
