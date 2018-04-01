package io.letitroll.common.mapper;

import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

/**
 * Base interface for mapping entities to data transfer objects
 * and vice versa.
 *
 * @param <T> entity type
 * @param <U> data transfer object type
 */
public interface Mapper<T, U> {

    U toDto(@NonNull T entity);

    T toEntity(@NonNull U dto);
}
