package io.letitroll.common.feature.mapper;

import io.letitroll.common.feature.domain.Feature;
import io.letitroll.common.feature.dto.FeatureDto;
import io.letitroll.common.mapper.Mapper;

public interface FeatureDto2EntityMapper extends Mapper<FeatureDto, Feature.FeatureBuilder> {
}
