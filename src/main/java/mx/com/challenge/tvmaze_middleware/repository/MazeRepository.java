package mx.com.challenge.tvmaze_middleware.repository;

import mx.com.challenge.tvmaze_middleware.document.ShowDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MazeRepository extends MongoRepository<ShowDocument, Long> {
}
