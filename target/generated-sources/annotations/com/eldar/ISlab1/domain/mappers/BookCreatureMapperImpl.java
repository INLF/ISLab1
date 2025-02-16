package com.eldar.ISlab1.domain.mappers;

import com.eldar.ISlab1.domain.dto.request.BookCreatureRequest;
import com.eldar.ISlab1.domain.dto.response.BookCreatureResponse;
import com.eldar.ISlab1.domain.model.BookCreatureEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T12:34:03+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class BookCreatureMapperImpl implements BookCreatureMapper {

    @Autowired
    private CoordinatesMapper coordinatesMapper;

    @Override
    public BookCreatureEntity toEntity(BookCreatureRequest request) {
        if ( request == null ) {
            return null;
        }

        BookCreatureEntity bookCreatureEntity = new BookCreatureEntity();

        bookCreatureEntity.setCoordinates( coordinatesMapper.toEntity( request.getCoordinates() ) );
        bookCreatureEntity.setId( request.getId() );
        bookCreatureEntity.setName( request.getName() );
        bookCreatureEntity.setAge( request.getAge() );
        bookCreatureEntity.setType( request.getType() );
        bookCreatureEntity.setAttackLevel( request.getAttackLevel() );

        return bookCreatureEntity;
    }

    @Override
    public BookCreatureResponse toResponse(BookCreatureEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BookCreatureResponse bookCreatureResponse = new BookCreatureResponse();

        bookCreatureResponse.setCoordinates( coordinatesMapper.toResponse( entity.getCoordinates() ) );
        bookCreatureResponse.setCityId( mapCityToId( entity.getCity() ) );
        bookCreatureResponse.setRingId( mapRingToId( entity.getRing() ) );
        bookCreatureResponse.setId( entity.getId() );
        bookCreatureResponse.setName( entity.getName() );
        bookCreatureResponse.setAge( entity.getAge() );
        bookCreatureResponse.setType( entity.getType() );
        bookCreatureResponse.setAttackLevel( entity.getAttackLevel() );
        bookCreatureResponse.setCreatedAt( entity.getCreatedAt() );
        bookCreatureResponse.setUpdatedAt( entity.getUpdatedAt() );

        return bookCreatureResponse;
    }
}
