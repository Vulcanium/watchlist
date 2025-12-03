package com.vulcanium.watchlist.controller;

import com.vulcanium.watchlist.exception.DuplicateTitleException;
import com.vulcanium.watchlist.model.WatchlistItem;
import com.vulcanium.watchlist.service.WatchlistService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WatchlistController {
    private static final String WATCHLIST_ITEM_FORM_VIEW_NAME = "watchlistItemForm";
    private static final String WATCHLIST_VIEW_NAME = "watchlist";

    WatchlistService watchlistService;

    public WatchlistController() {
        watchlistService = new WatchlistService();
    }

    @GetMapping("/watchlistItemForm")
    public String showWatchlistItemForm(@RequestParam(required = false) Integer id, Model model) {
        WatchlistItem watchlistItem = watchlistService.findWatchlistItemById(id);

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

        try {
            watchlistService.addOrUpdateWatchlistItem(watchlistItem);
        } catch (DuplicateTitleException e) {
            bindingResult.rejectValue("title", "", "This title already exists on your watchlist");
            return WATCHLIST_ITEM_FORM_VIEW_NAME;
        }

        return "redirect:/watchlist";
    }

    @GetMapping("/watchlist")
    public String getWatchlist(Model model) {
        model.addAttribute("watchlistItems", watchlistService.getWatchlistItems());
        model.addAttribute("numberOfMovies", watchlistService.getWatchlistItemsSize());

        return WATCHLIST_VIEW_NAME;
    }
}
