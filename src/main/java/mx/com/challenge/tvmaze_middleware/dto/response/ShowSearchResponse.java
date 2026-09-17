package mx.com.challenge.tvmaze_middleware.dto.response;

import java.util.List;

public record ShowSearchResponse(
        Long id,
        String name,
        String channel,
        String summary,
        List<String> genres
) {
}
