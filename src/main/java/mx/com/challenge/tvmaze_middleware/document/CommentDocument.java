package mx.com.challenge.tvmaze_middleware.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "comments")
public record CommentDocument(

        @Id
        String id,

        Long showId,

        String comment,

        Integer rating,

        Instant createdAt

) {
}