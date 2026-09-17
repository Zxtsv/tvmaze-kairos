package mx.com.challenge.tvmaze_middleware;

import org.springframework.boot.SpringApplication;

public class TestTvmazeMiddlewareApplication {

	public static void main(String[] args) {
		SpringApplication.from(TvmazeMiddlewareApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
