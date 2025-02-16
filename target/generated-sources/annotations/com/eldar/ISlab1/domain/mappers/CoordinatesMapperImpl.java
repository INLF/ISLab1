package com.eldar.ISlab1.domain.mappers;

import com.eldar.ISlab1.domain.dto.CoordinatedDto;
import com.eldar.ISlab1.domain.model.CoordinatesEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T12:34:03+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CoordinatesMapperImpl implements CoordinatesMapper {

    @Override
    public CoordinatesEntity toEntity(CoordinatedDto request) {
        if ( request == null ) {
            return null;
        }

        CoordinatesEntity coordinatesEntity = new CoordinatesEntity();

        coordinatesEntity.setId( request.getId() );
        coordinatesEntity.setX( request.getX() );
        coordinatesEntity.setY( request.getY() );

        return coordinatesEntity;
    }

    @Override
    public CoordinatedDto toResponse(CoordinatesEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CoordinatedDto.CoordinatedDtoBuilder coordinatedDto = CoordinatedDto.builder();

        coordinatedDto.id( entity.getId() );
        coordinatedDto.x( entity.getX() );
        coordinatedDto.y( entity.getY() );

        return coordinatedDto.build();
    }
}
