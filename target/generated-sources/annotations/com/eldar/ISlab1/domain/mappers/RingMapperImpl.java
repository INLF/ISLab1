package com.eldar.ISlab1.domain.mappers;

import com.eldar.ISlab1.domain.dto.request.RingRequest;
import com.eldar.ISlab1.domain.dto.response.RingResponse;
import com.eldar.ISlab1.domain.model.RingEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T12:34:03+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class RingMapperImpl implements RingMapper {

    @Override
    public RingEntity toEntity(RingRequest request) {
        if ( request == null ) {
            return null;
        }

        RingEntity ringEntity = new RingEntity();

        ringEntity.setId( request.getId() );
        ringEntity.setName( request.getName() );
        ringEntity.setPower( request.getPower() );
        ringEntity.setWeight( request.getWeight() );

        return ringEntity;
    }

    @Override
    public RingResponse toResponse(RingEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RingResponse.RingResponseBuilder ringResponse = RingResponse.builder();

        ringResponse.id( entity.getId() );
        ringResponse.name( entity.getName() );
        ringResponse.power( entity.getPower() );
        ringResponse.weight( entity.getWeight() );
        ringResponse.createdAt( entity.getCreatedAt() );
        ringResponse.updatedAt( entity.getUpdatedAt() );

        return ringResponse.build();
    }
}
