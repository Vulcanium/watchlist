package com.vulcanium.watchlist.actuator;

import com.vulcanium.watchlist.service.MovieRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HealthCheck implements HealthIndicator {

    private final MovieRatingService movieRatingService;

    @Override
    public Health health() {

        if (movieRatingService.getMovieRating("Star Wars") == null) {
            return Health.down().withDetail("Cause", "OMDb API is not available").build();
        }

        return Health.up().build();
    }
}
