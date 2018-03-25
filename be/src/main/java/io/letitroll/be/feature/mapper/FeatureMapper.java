package io.letitroll.be.feature.mapper;

import io.letitroll.be.feature.domain.Feature;
import io.letitroll.be.feature.dto.FeatureDto;

import javax.validation.constraints.NotNull;

public interface FeatureMapper {

    FeatureDto toDto(@NotNull Feature entity);

    Feature toEntity(@NotNull FeatureDto dto);
}
