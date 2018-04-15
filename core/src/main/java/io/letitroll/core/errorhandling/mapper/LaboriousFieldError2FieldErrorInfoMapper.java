package io.letitroll.core.errorhandling.mapper;

import io.letitroll.core.errorhandling.dto.FieldErrorInfo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Component
public class LaboriousFieldError2FieldErrorInfoMapper implements FieldError2FieldErrorInfoMapper {

    @Override
    public FieldErrorInfo map(@NonNull final FieldError source) {
        final String rejectedValue = Optional.ofNullable(source.getRejectedValue())
                .map(Object::toString)
                .orElse(null);

        return new FieldErrorInfo(source.getField(), source.getCode(), rejectedValue);
    }
}
