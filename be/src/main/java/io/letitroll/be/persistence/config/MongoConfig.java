package io.letitroll.be.persistence.config;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import static java.text.MessageFormat.format;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "io.letitroll.be.*.repository")
public class MongoConfig {

    @Bean
    @Profile("!test")
    public Mongobee mongobee(
            @Value("${spring.data.mongodb.host}") final String host,
            @Value("${spring.data.mongodb.port}") final String port,
            @Value("${spring.data.mongodb.database}") final String database,
            @Value("${spring.data.mongodb.username}") final String username,
            @Value("${spring.data.mongodb.password}") final String password,
            @Value("${mongobee.changelog-package}") final String changeLogPackage){

        final Mongobee runner = new Mongobee(format("mongodb://{0}:{1}@{2}:{3}/{4}", username, password, host, port, database));
        runner.setChangeLogsScanPackage(changeLogPackage);
        return runner;
    }
}
