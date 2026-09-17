package mx.com.challenge.tvmaze_middleware.dto.tvmaze;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TvMazeShow(
        Long id,
        String url,
        String name,
        String type,
        String language,
        List<String> genres,
        String status,
        Integer runtime,
        Integer averageRuntime,
        String premiered,
        String ended,
        String officialSite,
        TvMazeSchedule schedule,
        TvMazeRating rating,
        Integer weight,
        TvMazeNetwork network,
        TvMazeWebChannel webChannel,
        TvMazeCountry dvdCountry,
        TvMazeExternals externals,
        TvMazeImage image,
        String summary,
        Long updated,

        @JsonProperty("_links")
        TvMazeLinks links
) {
}