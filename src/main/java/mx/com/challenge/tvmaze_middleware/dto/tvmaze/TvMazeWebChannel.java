package mx.com.challenge.tvmaze_middleware.dto.tvmaze;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeWebChannel(
        Long id,
        String name,
        TvMazeCountry country,
        String officialSite
) {
}
