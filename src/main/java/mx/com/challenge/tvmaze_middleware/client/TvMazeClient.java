package mx.com.challenge.tvmaze_middleware.client;

import lombok.extern.slf4j.Slf4j;
import mx.com.challenge.tvmaze_middleware.dto.tvmaze.TvMazeSearchItem;
import mx.com.challenge.tvmaze_middleware.dto.tvmaze.TvMazeShow;
import mx.com.challenge.tvmaze_middleware.exception.ExternalApiException;
import mx.com.challenge.tvmaze_middleware.exception.TvNotFountException;
import mx.com.challenge.tvmaze_middleware.utils.Constants;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@Slf4j
public class TvMazeClient {

    private static final ParameterizedTypeReference<List<TvMazeSearchItem>>
            SEARCH_RESPONSE_TYPE = new ParameterizedTypeReference<>() {};

    private final RestClient tvMazeRestClient;

    public TvMazeClient(RestClient tvMazeRestClient) {
        this.tvMazeRestClient = tvMazeRestClient;
    }

    public List<TvMazeSearchItem> searchShows(String query) {

        List<TvMazeSearchItem> response = tvMazeRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(Constants.Endpoints.SEARCH_SHOWS)
                        .queryParam(Constants.ParamsClient.QUERY_PARAM, query)
                        .build())
                .retrieve()
                .body(SEARCH_RESPONSE_TYPE);

        log.info("[+] Search Shows RESPONSE: {}",response);
        return response != null ? response : List.of();
    }

    public TvMazeShow getShowById(Long showId) {
        try {
            TvMazeShow response = tvMazeRestClient
                    .get()
                    .uri(Constants.Endpoints.SHOW_BY_ID, showId)
                    .retrieve()
                    .body(TvMazeShow.class);

            log.info("[+] Show by Id RESPONSE: {}", response);
            return response;
        }catch (HttpClientErrorException.NotFound exception) {
            throw new TvNotFountException(showId);
        }catch (Exception exception) {
            throw new ExternalApiException(Constants.ERROR_EXTERNAL_API);
        }
    }
}
