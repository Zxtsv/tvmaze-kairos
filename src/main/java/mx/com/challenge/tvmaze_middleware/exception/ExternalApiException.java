package mx.com.challenge.tvmaze_middleware.exception;

public class ExternalApiException extends RuntimeException {

    public ExternalApiException(String message) {
        super(message);
    }
}