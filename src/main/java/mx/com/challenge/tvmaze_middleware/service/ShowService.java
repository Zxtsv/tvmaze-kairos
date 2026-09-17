package mx.com.challenge.tvmaze_middleware.service;

import lombok.extern.slf4j.Slf4j;
import mx.com.challenge.tvmaze_middleware.client.TvMazeClient;
import mx.com.challenge.tvmaze_middleware.dto.response.ShowSearchResponse;
import mx.com.challenge.tvmaze_middleware.dto.tvmaze.TvMazeShow;
import mx.com.challenge.tvmaze_middleware.mapper.ShowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ShowService {

    private final TvMazeClient tvMazeClient;
    private final ShowMapper showMapper;

    public ShowService(TvMazeClient tvMazeClient, ShowMapper showMapper) {
        this.tvMazeClient = tvMazeClient;
        this.showMapper = showMapper;
    }

    public List<ShowSearchResponse> searchShows(String searchQuery) {
        log.info("[+] Search Query: {}",searchQuery);
        return tvMazeClient
                .searchShows(searchQuery)
                .stream()
                .map(item -> item.show())
                .filter(Objects::nonNull)
                .map(showMapper::toSearchResponse)
                .toList();
    }

    public TvMazeShow getShowById(Long showId) {
        return tvMazeClient.getShowById(showId);
    }
}
