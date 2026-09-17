package mx.com.challenge.tvmaze_middleware.document;

import mx.com.challenge.tvmaze_middleware.dto.tvmaze.TvMazeShow;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "shows")
public record ShowDocument(

        @Id
        Long id,

        TvMazeShow show,

        Instant cachedAt
) {
}