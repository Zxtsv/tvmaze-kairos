package mx.com.challenge.tvmaze_middleware.mapper;

import lombok.extern.slf4j.Slf4j;
import mx.com.challenge.tvmaze_middleware.dto.response.ShowSearchResponse;
import mx.com.challenge.tvmaze_middleware.dto.tvmaze.TvMazeShow;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShowMapper {

    public ShowSearchResponse toSearchResponse(TvMazeShow show) {
        log.info("[+] Show Search: {}",show.toString());
        return new ShowSearchResponse(
                show.id(),
                show.name(),
                resolveChannel(show),
                show.summary(),
                show.genres()
        );
    }

    private String resolveChannel(TvMazeShow show) {
        log.info("[+] Resolve Channel: {}", show.toString());
        if (show.network() != null) {
            return show.network().name();
        }

        if (show.webChannel() != null) {
            return show.webChannel().name();
        }

        return null;
    }
}
