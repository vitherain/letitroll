package io.letitroll.be.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/*@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity*/
public class SecurityConfig {

    private static final Integer B_CRYPT_ROUNDS = 10;

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(B_CRYPT_ROUNDS);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .httpBasic().and()
                .formLogin();
        return http.build();
    }*/
}
