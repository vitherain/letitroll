package io.letitroll.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class,
		ReactiveSecurityAutoConfiguration.class
})
@EnableWebFlux
public class BeApplication {

	public static void main(final String[] args) {
		SpringApplication.run(BeApplication.class, args);
	}
}
