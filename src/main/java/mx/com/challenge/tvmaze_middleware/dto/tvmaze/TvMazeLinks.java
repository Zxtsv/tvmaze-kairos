package mx.com.challenge.tvmaze_middleware.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeLinks(
        TvMazeLink self,
        TvMazeLink previousepisode,
        TvMazeLink nextepisode
) {
}