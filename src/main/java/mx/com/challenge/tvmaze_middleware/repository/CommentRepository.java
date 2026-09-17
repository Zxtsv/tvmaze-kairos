package mx.com.challenge.tvmaze_middleware.repository;

import mx.com.challenge.tvmaze_middleware.document.CommentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<CommentDocument, String> {
    List<CommentDocument> findByShowId(Long showId);
    List<CommentDocument> findByShowIdIn(List<Long> showIds);
}