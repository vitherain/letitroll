package io.letitroll.core.errorhandling.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Instances are immutable.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ValidationErrorInfo {

    private final String code;
    private final String description;
    private final List<FieldErrorInfo> fieldErrors;

    public ValidationErrorInfo(@NonNull final String code) {
        this(code, null);
    }

    public ValidationErrorInfo(@NonNull final String code, @Nullable final String description) {
        this(code, description, new ArrayList<>());
    }

    public ValidationErrorInfo(@NonNull final String code, @Nullable final String description, @NonNull final List<FieldErrorInfo> fieldErrors) {
        this.code = code;
        this.description = description;
        this.fieldErrors = unmodifiableList(new ArrayList<>(fieldErrors));
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @NonNull
    public List<FieldErrorInfo> getFieldErrors() {
        return fieldErrors;
    }
}
