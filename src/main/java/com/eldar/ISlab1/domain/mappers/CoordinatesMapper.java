package com.eldar.ISlab1.domain.mappers;

import com.eldar.ISlab1.domain.dto.CoordinatedDto;
import com.eldar.ISlab1.domain.model.CoordinatesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordinatesMapper {
    CoordinatesEntity toEntity(CoordinatedDto request);
    CoordinatedDto toResponse(CoordinatesEntity entity);
}