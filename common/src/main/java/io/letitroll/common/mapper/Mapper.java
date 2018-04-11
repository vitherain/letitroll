package io.letitroll.common.mapper;

import org.springframework.lang.NonNull;

/**
 * Base interface for mapping object of one type to
 * object of another type.
 *
 * @param <A> first object's type
 * @param <B> second object's type
 */
public interface Mapper<A, B> {

    B map(@NonNull A source);
}
