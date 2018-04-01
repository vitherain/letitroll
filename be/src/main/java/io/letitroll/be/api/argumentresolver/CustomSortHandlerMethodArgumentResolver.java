package io.letitroll.be.api.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class CustomSortHandlerMethodArgumentResolver implements SortArgumentResolver {

    private static final String PARAMETER_NAME = "sort";
    private static final String PROPERTY_DELIMITER = ",";

    private static final String SORT_DEFAULTS_NAME = SortDefault.SortDefaults.class.getSimpleName();
    private static final String SORT_DEFAULT_NAME = SortDefault.class.getSimpleName();

    private Sort fallbackSort = null;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return Sort.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Mono<Object> resolveArgument(final MethodParameter parameter, final BindingContext bindingContext, final ServerWebExchange exchange) {
        return Mono.just(this.resolveSortArgument(parameter, bindingContext, exchange));
    }

    @Override
    public Sort resolveSortArgument(final MethodParameter parameter, final BindingContext bindingContext, final ServerWebExchange exchange) {
        final String[] directionParameter = exchange.getRequest().getQueryParams().getFirst(PARAMETER_NAME).split(PROPERTY_DELIMITER);

        // No parameter
        if (directionParameter == null) {
            return getDefaultFromAnnotationOrFallback(parameter);
        }

        // Single empty parameter, e.g "sort="
        if (directionParameter.length == 1 && !StringUtils.hasText(directionParameter[0])) {
            return getDefaultFromAnnotationOrFallback(parameter);
        }

        return parseParameterIntoSort(directionParameter);
    }

    /**
     * Reads the default {@link Sort} to be used from the given {@link MethodParameter}. Rejects the parameter if both an
     * {@link SortDefault.SortDefaults} and {@link SortDefault} annotation is found as we cannot build a reliable {@link Sort}
     * instance then (property ordering).
     *
     * @param parameter will never be {@literal null}.
     * @return the default {@link Sort} instance derived from the parameter annotations or the configured fallback-sort
     *         {@link #setFallbackSort(Sort)}.
     */
    private Sort getDefaultFromAnnotationOrFallback(MethodParameter parameter) {

        SortDefault.SortDefaults annotatedDefaults = parameter.getParameterAnnotation(SortDefault.SortDefaults.class);
        SortDefault annotatedDefault = parameter.getParameterAnnotation(SortDefault.class);

        if (annotatedDefault != null && annotatedDefaults != null) {
            throw new IllegalArgumentException(
                    String.format("Cannot use both @%s and @%s on parameter %s! Move %s into %s to define sorting order!",
                            SORT_DEFAULTS_NAME, SORT_DEFAULT_NAME, parameter.toString(), SORT_DEFAULT_NAME, SORT_DEFAULTS_NAME));
        }

        if (annotatedDefault != null) {
            return appendOrCreateSortTo(annotatedDefault, null);
        }

        if (annotatedDefaults != null) {
            Sort sort = null;
            for (SortDefault currentAnnotatedDefault : annotatedDefaults.value()) {
                sort = appendOrCreateSortTo(currentAnnotatedDefault, sort);
            }
            return sort;
        }

        return fallbackSort;
    }

    /**
     * Parses the given sort expressions into a {@link Sort} instance. The implementation expects the sources to be a
     * concatenation of Strings using the given delimiter. If the last element can be parsed into a {@link Sort.Direction} it's
     * considered a {@link Sort.Direction} and a simple property otherwise.
     *
     * @param source will never be {@literal null}.
     */
    private Sort parseParameterIntoSort(final String[] source) {

        final List<Sort.Order> allOrders = new ArrayList<>();

        for (final String part : source) {

            if (part == null) {
                continue;
            }

            final String[] elements = part.split(PROPERTY_DELIMITER);
            final Sort.Direction direction = elements.length == 0 ? null : Sort.Direction.fromString(elements[elements.length - 1]);

            for (int i = 0; i < elements.length; i++) {

                if (i == elements.length - 1 && direction != null) {
                    continue;
                }

                final String property = elements[i];

                if (!StringUtils.hasText(property)) {
                    continue;
                }

                allOrders.add(new Sort.Order(direction, property));
            }
        }

        return allOrders.isEmpty() ? null : Sort.by(allOrders);
    }
}
