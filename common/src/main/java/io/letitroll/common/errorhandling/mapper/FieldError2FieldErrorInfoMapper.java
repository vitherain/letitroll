package io.letitroll.common.errorhandling.mapper;

import io.letitroll.common.errorhandling.dto.FieldErrorInfo;
import io.letitroll.common.mapper.Mapper;
import org.springframework.validation.FieldError;

public interface FieldError2FieldErrorInfoMapper extends Mapper<FieldError, FieldErrorInfo> {
}
