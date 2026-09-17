package mx.com.challenge.tvmaze_middleware.controller;


import jakarta.validation.Valid;
import mx.com.challenge.tvmaze_middleware.dto.request.CreateCommentRequest;
import mx.com.challenge.tvmaze_middleware.dto.response.StatusResponse;
import mx.com.challenge.tvmaze_middleware.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shows")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{showId}/comments")
    public ResponseEntity<StatusResponse> createComment(@PathVariable Long showId, @Valid @RequestBody CreateCommentRequest request) {

        commentService.createComment(showId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new StatusResponse("CREATED"));
    }
}