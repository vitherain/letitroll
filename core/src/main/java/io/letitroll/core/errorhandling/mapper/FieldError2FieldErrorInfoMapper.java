package io.letitroll.core.errorhandling.mapper;

import io.letitroll.core.errorhandling.dto.FieldErrorInfo;
import io.letitroll.core.mapper.Mapper;
import org.springframework.validation.FieldError;

public interface FieldError2FieldErrorInfoMapper extends Mapper<FieldError, FieldErrorInfo> {
}
