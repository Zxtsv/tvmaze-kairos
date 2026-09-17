package mx.com.challenge.tvmaze_middleware.service;

import lombok.extern.slf4j.Slf4j;
import mx.com.challenge.tvmaze_middleware.client.TvMazeClient;
import mx.com.challenge.tvmaze_middleware.document.ShowDocument;
import mx.com.challenge.tvmaze_middleware.dto.response.ShowSearchResponse;
import mx.com.challenge.tvmaze_middleware.dto.tvmaze.TvMazeShow;
import mx.com.challenge.tvmaze_middleware.exception.ExternalApiException;
import mx.com.challenge.tvmaze_middleware.mapper.ShowMapper;
import mx.com.challenge.tvmaze_middleware.repository.MazeRepository;
import mx.com.challenge.tvmaze_middleware.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ShowService {

    private final TvMazeClient tvMazeClient;
    private final ShowMapper showMapper;
    private final MazeRepository mazeRepository;

    public ShowService(TvMazeClient tvMazeClient, ShowMapper showMapper, MazeRepository mazeRepository) {
        this.tvMazeClient = tvMazeClient;
        this.showMapper = showMapper;
        this.mazeRepository = mazeRepository;
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

        return mazeRepository.findById(showId).map(document -> {
                    log.info("Show {} found in MongoDB cache", showId);
                    return document.show();
                })
                .orElseGet(() -> fetchAndCacheShow(showId));
    }

    private TvMazeShow fetchAndCacheShow(Long showId) {

        log.info("Show {} not found in cache. Fetching from TVMaze", showId);

        TvMazeShow show = tvMazeClient.getShowById(showId);

        if (show == null) {
            throw new ExternalApiException(Constants.ERROR_EMPTY_RESPONSE_TVMAZE + showId);
        }

        ShowDocument document = new ShowDocument(show.id(), show, Instant.now());
        mazeRepository.save(document);

        log.info("Show {} stored in MongoDB cache", showId);

        return show;
    }
}
