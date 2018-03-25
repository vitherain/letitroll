package io.letitroll.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableScheduling
@EnableWebFlux
public class LetItRollApplication {

	public static void main(final String[] args) {
		SpringApplication.run(LetItRollApplication.class, args);
	}
}
