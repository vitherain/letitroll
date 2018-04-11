package io.letitroll.common.builder;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBuilder<T> {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    protected abstract T buildInternal();

    public T build() throws ConstraintViolationException {
        final T object = buildInternal();

        final Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(
                    new HashSet<ConstraintViolation<?>>(violations));
        }

        return object;
    }
}
