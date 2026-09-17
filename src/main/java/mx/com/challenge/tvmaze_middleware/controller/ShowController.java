package mx.com.challenge.tvmaze_middleware.controller;

import jakarta.validation.constraints.NotBlank;
import mx.com.challenge.tvmaze_middleware.dto.response.ShowSearchResponse;
import mx.com.challenge.tvmaze_middleware.dto.tvmaze.TvMazeShow;
import mx.com.challenge.tvmaze_middleware.service.ShowService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/search")
    public List<ShowSearchResponse> searchShows(@RequestParam("search_query") @NotBlank String searchQuery) {
        return showService.searchShows(searchQuery);
    }

    @GetMapping("/{showId}")
    public TvMazeShow getShowById(@PathVariable Long showId) {
        return showService.getShowById(showId);
    }
}
