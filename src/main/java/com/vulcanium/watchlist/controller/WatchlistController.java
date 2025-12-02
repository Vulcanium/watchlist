package com.vulcanium.watchlist.controller;

import com.vulcanium.watchlist.model.WatchlistItem;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WatchlistController {

    private static final String WATCHLIST_ITEM_FORM_VIEW_NAME = "watchlistItemForm";
    private static final String WATCHLIST_VIEW_NAME = "watchlist";

    private final List<WatchlistItem> watchlistItems = new ArrayList<>();
    private static int index = 1;


    @GetMapping("/watchlistItemForm")
    public String showWatchlistItemForm(@RequestParam(required = false) Integer id, Model model) {
        WatchlistItem watchlistItem = findWatchlistItemById(id);

        // If the watchlistItem does not exist, we display an empty web form.
        // Otherwise, we display a pre-filled web form.
        if (watchlistItem == null) {
            model.addAttribute("watchlistItem", new WatchlistItem());
        } else {
            model.addAttribute("watchlistItem", watchlistItem);
        }

        return WATCHLIST_ITEM_FORM_VIEW_NAME;
    }

    @PostMapping("/watchlistItemForm")
    public String submitWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return WATCHLIST_ITEM_FORM_VIEW_NAME;
        }

        if (itemAlreadyExists(watchlistItem.getTitle())) {
            bindingResult.rejectValue("title", "", "This title already exists on your watchlist");
            return WATCHLIST_ITEM_FORM_VIEW_NAME;
        }

        WatchlistItem existingWatchlistItem = findWatchlistItemById(watchlistItem.getId());

        // If the watchlist item does not exist, we create it.
        // Otherwise, we update it.
        if (existingWatchlistItem == null) {
            watchlistItem.setId(index++);
            watchlistItems.add(watchlistItem);
        } else {
            existingWatchlistItem.setTitle(watchlistItem.getTitle());
            existingWatchlistItem.setRating(watchlistItem.getRating());
            existingWatchlistItem.setPriority(watchlistItem.getPriority());
            existingWatchlistItem.setComment(watchlistItem.getComment());
        }

        return "redirect:/watchlist";
    }

    @GetMapping("/watchlist")
    public String getWatchlist(Model model) {
        model.addAttribute("watchlistItems", watchlistItems);
        model.addAttribute("numberOfMovies", watchlistItems.size());

        return WATCHLIST_VIEW_NAME;
    }

    private WatchlistItem findWatchlistItemById(Integer id) {

        for (WatchlistItem watchlistItem : watchlistItems) {
            if (watchlistItem.getId().equals(id)) {
                return watchlistItem;
            }
        }

        return null;
    }

    private boolean itemAlreadyExists(String title) {

        for (WatchlistItem watchlistItem : watchlistItems) {
            if (watchlistItem.getTitle().equals(title)) {
                return true;
            }
        }

        return false;
    }
}
