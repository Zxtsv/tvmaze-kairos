package mx.com.challenge.tvmaze_middleware.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeCountry(
        String name,
        String code,
        String timezone
) {
}