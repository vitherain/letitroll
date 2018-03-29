package io.letitroll.be.persistence.config;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

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
            @Value("${spring.data.mongodb.password}") final String password){

        final Mongobee runner = new Mongobee("mongodb://YOUR_DB_HOST:27017/DB_NAME");
        runner.setDbName("yourDbName");         // host must be set if not set in URI
        runner.setChangeLogsScanPackage(
                "com.example.yourapp.changelogs"); // the package to be scanned for changesets

        return runner;
    }
}
