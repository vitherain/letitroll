package io.letitroll.be.api.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;

public interface SortArgumentResolver extends HandlerMethodArgumentResolver {

    Sort resolveSortArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange);
}
