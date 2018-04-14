package io.letitroll.be.api.config;

import io.letitroll.be.api.argumentresolver.CustomPageableHandlerMethodArgumentResolver;
import io.letitroll.be.api.argumentresolver.CustomSortHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
public class ApiConfiguration implements WebFluxConfigurer {

    @Override
    public void configureArgumentResolvers(final ArgumentResolverConfigurer configurer) {
        final CustomSortHandlerMethodArgumentResolver sortResolver = new CustomSortHandlerMethodArgumentResolver();

        configurer.addCustomResolver(new CustomPageableHandlerMethodArgumentResolver(sortResolver));
        configurer.addCustomResolver(sortResolver);
    }
}
