package com.vulcanium.watchlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WatchlistItem {

    private Integer id;

    private String title;
    private String rating;
    private String priority;
    private String comment;
}
