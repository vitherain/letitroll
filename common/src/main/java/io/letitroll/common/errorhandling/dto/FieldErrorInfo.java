package io.letitroll.common.errorhandling.dto;

import org.springframework.lang.Nullable;

/**
 * Instances are immutable.
 */
public final class FieldErrorInfo {

    private final String fieldName;
    private final String errorType;
    private final String rejectedValue;

    public FieldErrorInfo(
            @Nullable final String fieldName,
            @Nullable final String errorType,
            @Nullable final String rejectedValue) {

        this.fieldName = fieldName;
        this.errorType = errorType;
        this.rejectedValue = rejectedValue;
    }

    @Nullable
    public String getFieldName() {
        return fieldName;
    }

    @Nullable
    public String getErrorType() {
        return errorType;
    }

    @Nullable
    public String getRejectedValue() {
        return rejectedValue;
    }
}
