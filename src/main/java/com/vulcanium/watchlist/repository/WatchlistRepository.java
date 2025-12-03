package com.vulcanium.watchlist.repository;

import com.vulcanium.watchlist.model.WatchlistItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WatchlistRepository {

    private final List<WatchlistItem> watchlistItems = new ArrayList<>();
    private static int index = 1;

    public List<WatchlistItem> getList() {
        return watchlistItems;
    }

    public void addItem(WatchlistItem watchlistItem) {
        watchlistItem.setId(index++);
        watchlistItems.add(watchlistItem);
    }

    public WatchlistItem findById(Integer id) {

        for (WatchlistItem watchlistItem : watchlistItems) {
            if (watchlistItem.getId().equals(id)) {
                return watchlistItem;
            }
        }

        return null;
    }

    public WatchlistItem findByTitle(Integer id, String title) {

        for (WatchlistItem watchlistItem : watchlistItems) {

            // The ID must be different while the title is the same for a duplicate to be detected.
            // Otherwise, it is the same watchlist item.
            if (!Objects.equals(watchlistItem.getId(), id) && watchlistItem.getTitle().equals(title)) {
                return watchlistItem;
            }
        }

        return null;
    }
}
