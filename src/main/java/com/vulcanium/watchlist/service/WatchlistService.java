package com.vulcanium.watchlist.service;

import com.vulcanium.watchlist.exception.DuplicateTitleException;
import com.vulcanium.watchlist.model.WatchlistItem;
import com.vulcanium.watchlist.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final MovieRatingService movieRatingService;

    public List<WatchlistItem> getWatchlistItems() {
        List<WatchlistItem> watchlistItems = watchlistRepository.getList();

        for (WatchlistItem watchlistItem : watchlistItems) {
            String rating = movieRatingService.getMovieRating(watchlistItem.getTitle());

            if (rating != null) {
                watchlistItem.setRating(rating);
            }
        }

        return watchlistItems;
    }

    public int getWatchlistItemsSize() {
        return watchlistRepository.getList().size();
    }

    public WatchlistItem findWatchlistItemById(Integer id) {
        return watchlistRepository.findById(id);
    }

    public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {
        Integer watchlistItemId = watchlistItem.getId();

        if (watchlistRepository.findByTitle(watchlistItemId, watchlistItem.getTitle()) != null) {
            throw new DuplicateTitleException();
        }

        WatchlistItem existingWatchlistItem = findWatchlistItemById(watchlistItemId);

        // If the watchlist item does not exist, we create it.
        // Otherwise, we update it.
        if (existingWatchlistItem == null) {
            watchlistRepository.addItem(watchlistItem);
        } else {
            existingWatchlistItem.setTitle(watchlistItem.getTitle());
            existingWatchlistItem.setRating(watchlistItem.getRating());
            existingWatchlistItem.setPriority(watchlistItem.getPriority());
            existingWatchlistItem.setComment(watchlistItem.getComment());
        }
    }
}
