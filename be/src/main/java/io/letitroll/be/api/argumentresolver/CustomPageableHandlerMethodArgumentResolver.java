package io.letitroll.be.api.argumentresolver;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class CustomPageableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final SortHandlerMethodArgumentResolver DEFAULT_SORT_RESOLVER = new SortHandlerMethodArgumentResolver();
    private static final String INVALID_DEFAULT_PAGE_SIZE = "Invalid default page size configured for method %s! Must not be less than one!";
    private static final String DEFAULT_PAGE_PARAMETER = "page";
    private static final String DEFAULT_SIZE_PARAMETER = "size";
    private static final int DEFAULT_MAX_PAGE_SIZE = 2000;
    private static final Pageable DEFAULT_PAGE_REQUEST = PageRequest.of(0, 20);

    private Pageable fallbackPageable = DEFAULT_PAGE_REQUEST;
    private SortArgumentResolver sortResolver;
    private String pageParameterName = DEFAULT_PAGE_PARAMETER;
    private String sizeParameterName = DEFAULT_SIZE_PARAMETER;
    private int maxPageSize = DEFAULT_MAX_PAGE_SIZE;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return Pageable.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Mono<Object> resolveArgument(final MethodParameter parameter, final BindingContext bindingContext, final ServerWebExchange exchange) {
        assertPageableUniqueness(parameter);
        final Pageable defaultOrFallback = this.getDefaultFromAnnotationOrFallback(parameter);
        final String pageString = exchange.getRequest().getQueryParams().getFirst(this.pageParameterName);
        final String pageSizeString = exchange.getRequest().getQueryParams().getFirst(this.sizeParameterName);
        boolean pageAndSizeGiven = StringUtils.hasText(pageString) && StringUtils.hasText(pageSizeString);
        if (!pageAndSizeGiven && defaultOrFallback == null) {
            return null;
        } else {
            final int page = StringUtils.hasText(pageString) ? this.parseAndApplyBoundaries(pageString, Integer.MAX_VALUE) : defaultOrFallback.getPageNumber();
            int pageSize = StringUtils.hasText(pageSizeString) ? this.parseAndApplyBoundaries(pageSizeString, this.maxPageSize, false) : defaultOrFallback.getPageSize();
            pageSize = pageSize < 1 ? defaultOrFallback.getPageSize() : pageSize;
            pageSize = pageSize > this.maxPageSize ? this.maxPageSize : pageSize;
            Sort sort = this.sortResolver.resolveArgument(parameter, bindingContext, exchange);
            sort = sort == null && defaultOrFallback != null ? defaultOrFallback.getSort() : sort;
            return Mono.just(PageRequest.of(page, pageSize, sort));
        }
    }

    public void assertPageableUniqueness(final MethodParameter parameter) {

        final Method method = parameter.getMethod();

        if (containsMoreThanOnePageableParameter(method)) {
            final Annotation[][] annotations = method.getParameterAnnotations();
            assertQualifiersFor(method.getParameterTypes(), annotations);
        }
    }

    private boolean containsMoreThanOnePageableParameter(final Method method) {

        boolean pageableFound = false;

        for (final Class<?> type : method.getParameterTypes()) {

            if (pageableFound && type.equals(Pageable.class)) {
                return true;
            }

            if (type.equals(Pageable.class)) {
                pageableFound = true;
            }
        }

        return false;
    }

    private void assertQualifiersFor(final Class<?>[] parameterTypes, final Annotation[][] annotations) {

        final Set<String> values = new HashSet<>();

        for (int i = 0; i < annotations.length; i++) {

            if (Pageable.class.equals(parameterTypes[i])) {

                final Qualifier qualifier = findAnnotation(annotations[i]);

                if (qualifier == null) {
                    throw new IllegalStateException(
                            "Ambiguous Pageable arguments in handler method. If you use multiple parameters of type Pageable you need to qualify them with @Qualifier");
                }

                if (values.contains(qualifier.value())) {
                    throw new IllegalStateException("Values of the user Qualifiers must be unique!");
                }

                values.add(qualifier.value());
            }
        }
    }

    private Qualifier findAnnotation(final Annotation[] annotations) {

        for (final Annotation annotation : annotations) {
            if (annotation instanceof Qualifier) {
                return (Qualifier) annotation;
            }
        }

        return null;
    }

    private Pageable getDefaultFromAnnotationOrFallback(final MethodParameter methodParameter) {

        if (methodParameter.hasParameterAnnotation(PageableDefault.class)) {
            return getDefaultPageRequestFrom(methodParameter);
        }

        return fallbackPageable;
    }

    private Pageable getDefaultPageRequestFrom(final MethodParameter parameter) {

        final PageableDefault defaults = parameter.getParameterAnnotation(PageableDefault.class);

        final Integer defaultPageNumber = defaults.page();
        final Integer defaultPageSize = defaults.size();

        if (defaultPageSize < 1) {
            final Method annotatedMethod = parameter.getMethod();
            throw new IllegalStateException(String.format(INVALID_DEFAULT_PAGE_SIZE, annotatedMethod));
        }

        if (defaults.sort().length == 0) {
            return PageRequest.of(defaultPageNumber, defaultPageSize);
        }

        return PageRequest.of(defaultPageNumber, defaultPageSize, defaults.direction(), defaults.sort());
    }

    /**
     * Tries to parse the given {@link String} into an integer and applies the given boundaries.
     * Will return 0 if the {@link String} cannot be parsed.
     *
     * @param parameter the parameter value.
     * @param upper the upper bound to be applied.
     */
    private int parseAndApplyBoundaries(final String parameter, final int upper) {

        try {
            final int parsed = Integer.parseInt(parameter);
            return parsed < 0 ? 0 : parsed > upper ? upper : parsed;
        } catch (final NumberFormatException e) {
            return 0;
        }
    }
}
