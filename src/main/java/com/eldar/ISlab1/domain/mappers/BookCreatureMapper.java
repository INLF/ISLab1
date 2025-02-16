package com.eldar.ISlab1.domain.mappers;

import com.eldar.ISlab1.domain.dto.request.BookCreatureRequest;
import com.eldar.ISlab1.domain.dto.response.BookCreatureResponse;
import com.eldar.ISlab1.domain.model.BookCreatureEntity;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import com.eldar.ISlab1.domain.model.RingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = CoordinatesMapper.class)
public interface BookCreatureMapper {

    @Mapping(target = "coordinates", source = "coordinates")
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "ring", ignore = true)
    BookCreatureEntity toEntity(BookCreatureRequest request);

    @Mapping(target = "coordinates", source = "coordinates")
    // For the response, if you also have fields to ignore:
    // If BookCreatureResponse has cityId, ringId, or similar, you need a proper mapping or ignoring
    @Mapping(target = "cityId", source = "city", qualifiedByName = "mapCityToId")
    @Mapping(target = "ringId", source = "ring", qualifiedByName = "mapRingToId")
    BookCreatureResponse toResponse(BookCreatureEntity entity);

    @Named("mapCityToId")
    default Long mapCityToId(MagicCityEntity city) {
        System.out.println("mapCityToId cityId = " + city);
        return (city != null) ? city.getId() : null;
    }

    @Named("mapRingToId")
    default Long mapRingToId(RingEntity ring) {
        return (ring != null) ? ring.getId() : null;
    }
}
