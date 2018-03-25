package io.letitroll.be.shared.mapper;

import javax.validation.constraints.NotNull;

/**
 * Base interface for mapping entities to data transfer objects
 * and vice versa.
 *
 * @param <T> entity type
 * @param <U> data transfer object type
 */
public interface Mapper<T, U> {

    U toDto(@NotNull T entity);

    T toEntity(@NotNull U dto);
}
