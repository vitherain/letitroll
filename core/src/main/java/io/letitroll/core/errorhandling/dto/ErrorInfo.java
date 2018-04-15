package io.letitroll.core.errorhandling.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * Instances are immutable.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ErrorInfo {

    private final String code;
    private final String description;

    public ErrorInfo(@NonNull final String code) {
        this(code, null);
    }

    public ErrorInfo(@NonNull final String code, @Nullable final String description) {
        this.code = code;
        this.description = description;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @Nullable
    public String getDescription() {
        return description;
    }
}
