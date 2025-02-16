package com.eldar.ISlab1.domain.mappers;


import com.eldar.ISlab1.domain.dto.request.MagicCityRequest;
import com.eldar.ISlab1.domain.dto.response.MagicCityResponse;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MagicCityMapper {
    MagicCityEntity toEntity(MagicCityRequest request);
    MagicCityResponse toResponse(MagicCityEntity entity);
}
