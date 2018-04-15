package io.letitroll.core.errorhandling.handler;

import io.letitroll.core.errorhandling.dto.ErrorInfo;
import io.letitroll.core.errorhandling.dto.FieldErrorInfo;
import io.letitroll.core.errorhandling.dto.ValidationErrorInfo;
import io.letitroll.core.errorhandling.exception.SecurityViolationException;
import io.letitroll.core.errorhandling.mapper.FieldError2FieldErrorInfoMapper;
import io.letitroll.core.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    private final UserService userService;
    private final FieldError2FieldErrorInfoMapper fieldErrorMapper;

    @Autowired
    public ControllerExceptionHandler(
            final UserService userService,
            final FieldError2FieldErrorInfoMapper fieldErrorMapper) {

        this.userService = userService;
        this.fieldErrorMapper = fieldErrorMapper;
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SecurityViolationException.class)
    public ErrorInfo handleSecurityViolationException(final SecurityViolationException e) {

        final String currentUserId = userService.getCurrentUserId();
        LOGGER.error("error=security_violated user_id={}", currentUserId, e);
        return new ErrorInfo("SECURITY_VIOLATION", e.getMessage());
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorInfo handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

        final String errorDescription = "The request validation did not pass";
        final List<FieldErrorInfo> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldErrorMapper::map)
                .collect(toList());

        return new ValidationErrorInfo("VALIDATION_ERROR", errorDescription, fieldErrors);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleException(final Exception e) {

        LOGGER.error(e.getMessage(), e);
        return new ErrorInfo("UNEXPECTED_ERROR", e.getMessage());
    }
}
