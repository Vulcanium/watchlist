package com.vulcanium.watchlist.model;

import com.vulcanium.watchlist.validation.GoodMovie;
import com.vulcanium.watchlist.validation.Priority;
import com.vulcanium.watchlist.validation.Rating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@GoodMovie
public class WatchlistItem {

    private Integer id;

    @NotBlank(message = "Please enter the title")
    private String title;

    @Rating
    private String rating;

    @Priority
    private String priority;

    @Size(max = 50, message = "Comment should be maximum 50 characters")
    private String comment;
}
