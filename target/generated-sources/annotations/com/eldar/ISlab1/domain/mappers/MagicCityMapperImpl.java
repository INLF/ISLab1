package com.eldar.ISlab1.domain.mappers;

import com.eldar.ISlab1.domain.dto.request.MagicCityRequest;
import com.eldar.ISlab1.domain.dto.response.MagicCityResponse;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T12:34:03+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class MagicCityMapperImpl implements MagicCityMapper {

    @Override
    public MagicCityEntity toEntity(MagicCityRequest request) {
        if ( request == null ) {
            return null;
        }

        MagicCityEntity magicCityEntity = new MagicCityEntity();

        magicCityEntity.setId( request.getId() );
        magicCityEntity.setName( request.getName() );
        magicCityEntity.setArea( request.getArea() );
        magicCityEntity.setPopulation( request.getPopulation() );
        magicCityEntity.setEstablishmentAt( request.getEstablishmentAt() );
        magicCityEntity.setGovernorType( request.getGovernorType() );
        magicCityEntity.setPopulationDensity( request.getPopulationDensity() );
        magicCityEntity.setIsCapital( request.getIsCapital() );

        return magicCityEntity;
    }

    @Override
    public MagicCityResponse toResponse(MagicCityEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MagicCityResponse.MagicCityResponseBuilder magicCityResponse = MagicCityResponse.builder();

        magicCityResponse.id( entity.getId() );
        magicCityResponse.name( entity.getName() );
        magicCityResponse.area( entity.getArea() );
        magicCityResponse.population( entity.getPopulation() );
        magicCityResponse.populationDensity( entity.getPopulationDensity() );
        magicCityResponse.establishmentAt( entity.getEstablishmentAt() );
        magicCityResponse.governorType( entity.getGovernorType() );
        magicCityResponse.isCapital( entity.getIsCapital() );
        magicCityResponse.createdAt( entity.getCreatedAt() );
        magicCityResponse.updatedAt( entity.getUpdatedAt() );

        return magicCityResponse.build();
    }
}
