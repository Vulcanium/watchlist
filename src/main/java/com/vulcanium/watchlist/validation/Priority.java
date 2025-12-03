package com.vulcanium.watchlist.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // To indicate that the custom annotation should be used on instance variables (fields)
@Retention(RetentionPolicy.RUNTIME) // To indicate that the custom annotation should be effective at runtime
@Constraint(validatedBy = PriorityValidator.class) // To specify that the logic of this custom validation annotation is implemented in the PriorityValidator class
public @interface Priority {

    String message() default "Please enter L, M or H for priority";

    // To specify by default to Spring so that it recognizes it as a custom annotation
    Class<?>[] groups() default {};

    // To specify by default to Spring so that it recognizes it as a custom annotation
    Class<? extends Payload>[] payload() default {};
}
