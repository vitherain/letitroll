package io.letitroll.be.shared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "io.letitroll.be.*.repository")
public class MongoConfig {
}
