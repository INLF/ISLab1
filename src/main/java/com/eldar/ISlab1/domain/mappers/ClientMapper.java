package com.eldar.ISlab1.domain.mappers;

import com.eldar.ISlab1.domain.dto.request.ClientRequest;
import com.eldar.ISlab1.domain.dto.response.ClientResponse;
import com.eldar.ISlab1.domain.dto.response.MagicCityResponse;
import com.eldar.ISlab1.domain.model.ClientEntity;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientEntity toEntity(ClientRequest request);
    ClientResponse toResponse(ClientEntity entity);
}
