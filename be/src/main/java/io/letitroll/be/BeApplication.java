package io.letitroll.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class
})
@ComponentScan(
		basePackages = { "io.letitroll.be", "io.letitroll.common" },
		excludeFilters = {
				@ComponentScan.Filter(type = FilterType.CUSTOM, classes = { TypeExcludeFilter.class }),
				@ComponentScan.Filter(type = FilterType.CUSTOM, classes = { AutoConfigurationExcludeFilter.class })
		}
)
@EnableScheduling
public class BeApplication {

	public static void main(final String[] args) {
		SpringApplication.run(BeApplication.class, args);
	}
}
