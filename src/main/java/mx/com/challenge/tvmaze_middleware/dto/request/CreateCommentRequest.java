package mx.com.challenge.tvmaze_middleware.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentRequest(

        @NotBlank(message = "Comment is required")
        String comment,

        @NotNull(message = "Rating is required")
        @Min(value = 0, message = "Rating must be greater than or equal to 0")
        @Max(value = 5, message = "Rating must be less than or equal to 5")
        Integer rating

) {
}