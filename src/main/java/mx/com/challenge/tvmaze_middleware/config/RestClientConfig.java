package mx.com.challenge.tvmaze_middleware.config;

import mx.com.challenge.tvmaze_middleware.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient tvMazeRestClient(
            @Value("${tvmaze.api.base-url}") String baseUrl
    ) {
        return RestClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.USER_AGENT, Constants.ParamsClient.USER_AGENT_REST_CLIENT).build();
    }
}
