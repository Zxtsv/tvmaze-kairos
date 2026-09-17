package mx.com.challenge.tvmaze_middleware.utils;

public class Constants {
    public static class Endpoints{
        public static final String SEARCH_SHOWS = "/search/shows";
        public static final String SHOW_BY_ID = "/shows/{showId}";
    }
    public static class ParamsClient{
        public static final String QUERY_PARAM = "q";
        public static String USER_AGENT_REST_CLIENT = "tvmaze-middleware/1.0";
    }
    public static final String ERROR_EXTERNAL_API = "Error communicating with TVMaze";
    public static final String ERROR_EMPTY_RESPONSE_TVMAZE = "TVMaze returned an empty response for show: ";
}
