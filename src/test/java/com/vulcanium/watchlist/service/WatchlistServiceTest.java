package com.vulcanium.watchlist.service;

import com.vulcanium.watchlist.model.WatchlistItem;
import com.vulcanium.watchlist.repository.WatchlistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WatchlistServiceTest {

    @Mock
    private WatchlistRepository mockWatchlistRepository;

    @Mock
    private MovieRatingService mockMovieRatingService;

    @InjectMocks
    private WatchlistService watchlistService;

    @Test
    public void testGetWatchlistItemsReturnsAllItemsFromRepository() {

        // Arrange
        WatchlistItem mockItem1 = new WatchlistItem(1, "Star Wars", "7.7", "H", "");
        WatchlistItem mockItem2 = new WatchlistItem(2, "Star Trek", "5.0", "M", "");
        List<WatchlistItem> mockItems = Arrays.asList(mockItem1, mockItem2);

        when(mockWatchlistRepository.getList()).thenReturn(mockItems);

        // Act
        List<WatchlistItem> result = watchlistService.getWatchlistItems();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Star Wars", result.get(0).getTitle());
        assertEquals("Star Trek", result.get(1).getTitle());
    }

    @Test
    public void testGetWatchlistItemsRatingFromOMDbServiceOverrideTheValueInItems() {

        // Arrange
        WatchlistItem mockItem1 = new WatchlistItem(1, "Star Wars", "7.7", "H", "");
        List<WatchlistItem> mockItems = List.of(mockItem1);

        when(mockWatchlistRepository.getList()).thenReturn(mockItems);
        when(mockMovieRatingService.getMovieRating(any(String.class))).thenReturn("10");

        // Act
        List<WatchlistItem> result = watchlistService.getWatchlistItems();

        // Assert
        assertEquals(1, result.size());
        assertEquals("10", result.getFirst().getRating());
    }

    @Test
    public void testGetWatchlistItemsSizeFromRepository() {

        // Arrange
        WatchlistItem mockItem1 = new WatchlistItem(1, "Star Wars", "7.7", "H", "");
        WatchlistItem mockItem2 = new WatchlistItem(2, "Star Trek", "5.0", "M", "");
        List<WatchlistItem> mockItems = Arrays.asList(mockItem1, mockItem2);

        when(mockWatchlistRepository.getList()).thenReturn(mockItems);

        // Act
        int result = watchlistService.getWatchlistItemsSize();

        // Assert
        assertEquals(2, result);
    }
}
