package io.letitroll.core.feature.domain;

import org.junit.Test;

import javax.validation.ConstraintViolationException;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FeatureBuilderTest {

    @Test
    public void featureShouldNotBeCreatedOnConstraintViolation() {
        assertThatThrownBy(Feature.builder()
                .name("name")
                .key("key")
                .description("description")
                .tags(emptySet())
                .type(null)
                .project(null)
        ::build).isExactlyInstanceOf(ConstraintViolationException.class);
    }
}