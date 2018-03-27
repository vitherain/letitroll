package io.letitroll.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class BeApplication {

	public static void main(final String[] args) {
		SpringApplication.run(BeApplication.class, args);
	}
}
