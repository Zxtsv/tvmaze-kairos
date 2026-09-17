package mx.com.challenge.tvmaze_middleware.dto.response;

public record CommentResponse(
        String comment,
        Integer rating
) {
}