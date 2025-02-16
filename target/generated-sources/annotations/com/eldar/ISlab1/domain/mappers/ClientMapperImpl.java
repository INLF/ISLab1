package com.eldar.ISlab1.domain.mappers;

import com.eldar.ISlab1.domain.dto.request.ClientRequest;
import com.eldar.ISlab1.domain.dto.response.ClientResponse;
import com.eldar.ISlab1.domain.model.ClientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-16T12:34:03+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientEntity toEntity(ClientRequest request) {
        if ( request == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId( request.getId() );
        clientEntity.setName( request.getName() );
        clientEntity.setLogin( request.getLogin() );
        clientEntity.setPassword( request.getPassword() );

        return clientEntity;
    }

    @Override
    public ClientResponse toResponse(ClientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setId( entity.getId() );
        clientResponse.setName( entity.getName() );
        clientResponse.setLogin( entity.getLogin() );
        clientResponse.setRole( entity.getRole() );
        clientResponse.setIsApproved( entity.getIsApproved() );
        clientResponse.setCreatedAt( entity.getCreatedAt() );
        clientResponse.setUpdatedAt( entity.getUpdatedAt() );

        return clientResponse;
    }
}
