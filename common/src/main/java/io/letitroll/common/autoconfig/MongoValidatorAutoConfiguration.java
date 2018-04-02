package io.letitroll.common.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MongoValidatorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ValidatingMongoEventListener.class)
    public ValidatingMongoEventListener validatingMongoEventListener(final LocalValidatorFactoryBean validator) {
        return new ValidatingMongoEventListener(validator);
    }

    @Bean
    @ConditionalOnMissingBean(LocalValidatorFactoryBean.class)
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
