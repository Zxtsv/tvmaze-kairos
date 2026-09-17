package mx.com.challenge.tvmaze_middleware.mapper;

import lombok.extern.slf4j.Slf4j;
import mx.com.challenge.tvmaze_middleware.dto.response.CommentResponse;
import mx.com.challenge.tvmaze_middleware.dto.response.ShowDetailResponse;
import mx.com.challenge.tvmaze_middleware.dto.response.ShowSearchResponse;
import mx.com.challenge.tvmaze_middleware.dto.tvmaze.TvMazeShow;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ShowMapper {

    public ShowSearchResponse toSearchResponse(TvMazeShow show, List<CommentResponse> comments) {
        log.info("[+] Show Search: {}",show.toString());
        return new ShowSearchResponse(
                show.id(),
                show.name(),
                resolveChannel(show),
                show.summary(),
                show.genres(),
                comments
        );
    }

    public ShowDetailResponse toDetailResponse(TvMazeShow show, List<CommentResponse> comments) {
        log.info("[+] Show toDetailResponse: {}",show.toString());
        log.info("[+] Comments toDetailResponse: {}",comments.toString());
        return new ShowDetailResponse(
                show.id(),
                show.url(),
                show.name(),
                show.type(),
                show.language(),
                show.genres(),
                show.status(),
                show.runtime(),
                show.averageRuntime(),
                show.premiered(),
                show.ended(),
                show.officialSite(),
                show.schedule(),
                show.rating(),
                show.weight(),
                show.network(),
                show.webChannel(),
                show.dvdCountry(),
                show.externals(),
                show.image(),
                show.summary(),
                show.updated(),
                show.links(),
                comments
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
