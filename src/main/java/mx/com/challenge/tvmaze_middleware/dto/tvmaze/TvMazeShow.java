package mx.com.challenge.tvmaze_middleware.dto.tvmaze;

import java.util.List;

public record TvMazeShow(
        Long id,
        String name,
        TvMazeChannel network,
        TvMazeChannel webChannel,
        String summary,
        List<String> genres
) {
}
