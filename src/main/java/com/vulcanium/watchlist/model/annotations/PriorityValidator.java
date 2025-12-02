package com.vulcanium.watchlist.model.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<Priority, String> {

    // The validation logic is a single-character string that must be 'L', 'M', or 'H'
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.trim().length() == 1 && "LMH".contains(value.trim());
    }
}
