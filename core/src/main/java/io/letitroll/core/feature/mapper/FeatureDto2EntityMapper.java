package io.letitroll.core.feature.mapper;

import io.letitroll.core.feature.domain.Feature;
import io.letitroll.core.feature.dto.FeatureDto;
import io.letitroll.core.mapper.Mapper;

public interface FeatureDto2EntityMapper extends Mapper<FeatureDto, Feature.FeatureBuilder> {
}
