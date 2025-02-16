package com.eldar.ISlab1.domain.mappers;

import com.eldar.ISlab1.domain.dto.request.MagicCityRequest;
import com.eldar.ISlab1.domain.dto.request.RingRequest;
import com.eldar.ISlab1.domain.dto.response.MagicCityResponse;
import com.eldar.ISlab1.domain.dto.response.RingResponse;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import com.eldar.ISlab1.domain.model.RingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RingMapper {
    RingEntity toEntity(RingRequest request);
    RingResponse toResponse(RingEntity entity);
}
