package com.vulcanium.watchlist.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<Rating, String> {

    // The validation logic is a number that must be between 1 and 10
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        double number;

        try {
            number = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return false;
        }

        return number <= 10 && number >= 1;
    }
}
