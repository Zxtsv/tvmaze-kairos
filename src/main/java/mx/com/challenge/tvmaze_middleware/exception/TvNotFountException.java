package mx.com.challenge.tvmaze_middleware.exception;

public class TvNotFountException extends RuntimeException{
    public TvNotFountException(Long showId){
        super("Show not found with id " + showId);
    }
}
