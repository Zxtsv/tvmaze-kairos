package mx.com.challenge.tvmaze_middleware.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeExternals(
        Integer tvrage,
        Integer thetvdb,
        String imdb
) {
}