package com.vulcanium.watchlist.model.annotations;

import com.vulcanium.watchlist.model.WatchlistItem;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {

    // The validation logic is that when a movie is rated at least 8, its associated priority must be at least M (Medium)
    @Override
    public boolean isValid(WatchlistItem value, ConstraintValidatorContext constraintValidatorContext) {
        return !(Double.parseDouble(value.getRating()) >= 8 && "L".equals(value.getPriority()));
    }
}
