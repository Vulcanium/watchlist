package com.vulcanium.watchlist.service;

import com.vulcanium.watchlist.configuration.OMDbApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.node.ObjectNode;

@Service
@RequiredArgsConstructor
public class MovieRatingService {

    private final OMDbApiProperties props;

    public String getMovieRating(String title) {

        // Building the URL to search for a movie by its title using the OMDb API
        String baseApiUrl = props.getApiUrl();
        String searchMovieTitleUrl = baseApiUrl + "t=" + title;

        try {
            // HTTP GET request on the OMDb API
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ObjectNode> response = restTemplate.getForEntity(searchMovieTitleUrl, ObjectNode.class);

            // Extract and return the rating from the response returned by the API
            ObjectNode jsonObject = response.getBody();

            return jsonObject.path("imdbRating").asString();
        } catch (Exception e) {
            System.out.println("Something went wrong while calling OMDb API: " + e.getMessage());
            return null;
        }
    }
}
