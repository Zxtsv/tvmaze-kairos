package mx.com.challenge.tvmaze_middleware.service;

import lombok.extern.slf4j.Slf4j;
import mx.com.challenge.tvmaze_middleware.document.CommentDocument;
import mx.com.challenge.tvmaze_middleware.dto.request.CreateCommentRequest;
import mx.com.challenge.tvmaze_middleware.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(Long showId, CreateCommentRequest request) {

        CommentDocument comment = new CommentDocument(null, showId, request.comment(), request.rating(), Instant.now());

        log.info("[+] Create comment: {}",comment.toString());
        commentRepository.save(comment);
    }
}