package mx.com.challenge.tvmaze_middleware.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import mx.com.challenge.tvmaze_middleware.dto.tvmaze.*;

import java.util.List;

public record ShowDetailResponse(
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
        TvMazeLinks links,
        List<CommentResponse> comments
) {
}