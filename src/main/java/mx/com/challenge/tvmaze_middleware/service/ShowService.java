package mx.com.challenge.tvmaze_middleware.service;

import lombok.extern.slf4j.Slf4j;
import mx.com.challenge.tvmaze_middleware.client.TvMazeClient;
import mx.com.challenge.tvmaze_middleware.document.CommentDocument;
import mx.com.challenge.tvmaze_middleware.document.ShowDocument;
import mx.com.challenge.tvmaze_middleware.dto.response.CommentResponse;
import mx.com.challenge.tvmaze_middleware.dto.response.ShowDetailResponse;
import mx.com.challenge.tvmaze_middleware.dto.response.ShowSearchResponse;
import mx.com.challenge.tvmaze_middleware.dto.tvmaze.TvMazeShow;
import mx.com.challenge.tvmaze_middleware.exception.ExternalApiException;
import mx.com.challenge.tvmaze_middleware.mapper.ShowMapper;
import mx.com.challenge.tvmaze_middleware.repository.CommentRepository;
import mx.com.challenge.tvmaze_middleware.repository.MazeRepository;
import mx.com.challenge.tvmaze_middleware.utils.Constants;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShowService {

    private final TvMazeClient tvMazeClient;
    private final ShowMapper showMapper;
    private final MazeRepository mazeRepository;
    private final CommentRepository commentRepository;

    public ShowService(TvMazeClient tvMazeClient, ShowMapper showMapper, MazeRepository mazeRepository, CommentRepository commentRepository) {
        this.tvMazeClient = tvMazeClient;
        this.showMapper = showMapper;
        this.mazeRepository = mazeRepository;
        this.commentRepository = commentRepository;
    }

    public List<ShowSearchResponse> searchShows(String searchQuery) {
        List<TvMazeShow> shows = tvMazeClient
                .searchShows(searchQuery)
                .stream()
                .map(item -> item.show())
                .filter(Objects::nonNull)
                .toList();

        log.info("[+] Shows Service: {}",shows.toString());

        if (shows.isEmpty()) {
            return List.of();
        }

        List<Long> showIds = shows.stream().map(TvMazeShow::id).toList();

        log.info("[+] Shows Ids Service: {}",showIds.toString());

        Map<Long, List<CommentResponse>> commentsByShowId =
                commentRepository
                        .findByShowIdIn(showIds)
                        .stream()
                        .collect(Collectors.groupingBy(
                                CommentDocument::showId,
                                Collectors.mapping(
                                        comment -> new CommentResponse(comment.comment(), comment.rating()), Collectors.toList()
                                )
                        ));

        log.info("[+] CommentsByShowId Service: {}",commentsByShowId);

        return shows.stream()
                .map(show -> showMapper.toSearchResponse(show, commentsByShowId.getOrDefault(show.id(), List.of())))
                .toList();
    }

    public ShowDetailResponse getShowById(Long showId) {
        TvMazeShow object = mazeRepository.findById(showId).map(document -> {
                    log.info("Show {} found in MongoDB cache", showId);
                    return document.show();
                })
                .orElseGet(() -> fetchAndCacheShow(showId));

        List<CommentResponse> comments = commentRepository
                .findByShowId(showId)
                .stream()
                .map(comment -> new CommentResponse(
                        comment.comment(),
                        comment.rating()
                ))
                .toList();

        log.info("[+] GetShowById: {}",comments);

        return showMapper.toDetailResponse(object,comments);
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
