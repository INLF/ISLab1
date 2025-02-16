package com.eldar.ISlab1.service.impl;

import com.eldar.ISlab1.domain.dto.request.MagicCityRequest;
import com.eldar.ISlab1.domain.dto.response.MagicCityResponse;
import com.eldar.ISlab1.domain.mappers.MagicCityMapper;
import com.eldar.ISlab1.domain.model.BookCreatureType;
import com.eldar.ISlab1.domain.model.ClientEntity;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import com.eldar.ISlab1.domain.model.UserRole;
import com.eldar.ISlab1.repository.MagicCityRepository;
import com.eldar.ISlab1.repository.specs.MagicCitySpecification;
import com.eldar.ISlab1.service.MagicCityService;
import com.eldar.ISlab1.util.SecurityUtils;
import com.eldar.ISlab1.ws.controllers.WebSocketController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MagicCityServiceImpl implements MagicCityService {
    private final MagicCityRepository repository;
    private final MagicCityMapper magicCityMapper;
    private final MagicCityRepository magicCityRepository;
    private final WebSocketController webSocketController;

    public void destroyElfCities() {
        magicCityRepository.deleteAllByGovernorType(BookCreatureType.ELF);
    }

    @Override
    public MagicCityResponse createCity(MagicCityRequest request) {
        try {
            MagicCityEntity cityEntity = magicCityMapper.toEntity(request);
            cityEntity.setCreator(SecurityUtils.getCurrentClientAuthentication());

            cityEntity = repository.save(cityEntity);
            webSocketController.sendUpdate("update");
            return magicCityMapper.toResponse(cityEntity);
        } catch (Exception e) {
            log.error("Error creating city: ", e);
            throw new RuntimeException("Error creating city", e);
        }
    }

    @Override
    public boolean softDeleteCity(long id) {
        try {
            ClientEntity currentClient = SecurityUtils.getCurrentClientAuthentication();
            MagicCityEntity cityToDelete = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("City with ID " + id + " not found"));

            if (currentClient == null) {
                throw new AccessDeniedException("Client not authenticated");
            }

            if (currentClient.getRole() != UserRole.ADMIN && !currentClient.getId().equals(cityToDelete.getCreator().getId())) {
                throw new AccessDeniedException(
                        String.format("Client with ID %d is not allowed to delete city created by ID %d",
                                currentClient.getId(),
                                cityToDelete.getCreator().getId()
                        )
                );
            }

            repository.deleteById(id);
            webSocketController.sendUpdate("update");
            return true;
        } catch (Exception e) {
            log.error("Error soft deleting city with id {}: ", id, e);
            return false;
        }
    }

    @Override
    public MagicCityResponse getCityEntityById(long id) {
        try {
            MagicCityEntity cityEntity = repository.getReferenceById(id);
            return magicCityMapper.toResponse(cityEntity);
        } catch (Exception e) {
            log.error("Error retrieving city with id {}: ", id, e);
            throw new RuntimeException("Error retrieving city", e);
        }
    }

    @Override
    public MagicCityResponse updateCity(MagicCityRequest request) {
        try {
            ClientEntity currentClient = SecurityUtils.getCurrentClientAuthentication();
            MagicCityEntity cityToUpdate = repository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("City with ID " + request.getId() + " not found"));

            if (currentClient == null) {
                throw new AccessDeniedException("Client not authenticated");
            }

            if (currentClient.getRole() != UserRole.ADMIN && !currentClient.getId().equals(cityToUpdate.getCreator().getId())) {
                throw new AccessDeniedException(
                        String.format("Client with ID %d is not allowed to update city created by ID %d",
                                currentClient.getId(),
                                cityToUpdate.getCreator().getId()
                        )
                );
            }

            cityToUpdate.setName(request.getName());
            cityToUpdate.setArea(request.getArea());
            cityToUpdate.setPopulation(request.getPopulation());
            cityToUpdate.setEstablishmentAt(request.getEstablishmentAt());
            cityToUpdate.setPopulationDensity(request.getPopulationDensity());
            cityToUpdate.setIsCapital(request.getIsCapital());
            cityToUpdate.setGovernorType(request.getGovernorType());

            MagicCityEntity updatedCityEntity = repository.save(cityToUpdate);

            webSocketController.sendUpdate("update");
            return magicCityMapper.toResponse(updatedCityEntity);
        } catch (Exception e) {
            log.error("Error updating city: ", e);
            throw new RuntimeException("Error updating city", e);
        }
    }

    public Page<MagicCityResponse> getFilteredCities(MagicCityRequest filter, Pageable pageable) {
        MagicCitySpecification specification = new MagicCitySpecification(
                filter.getName(),
                filter.getArea(),
                filter.getPopulation(),
                filter.getEstablishmentAt(),
                filter.getPopulationDensity(),
                filter.getIsCapital(),
                filter.getGovernorType()
        );

        return magicCityRepository
                .findAll(specification, pageable)
                .map(magicCityMapper::toResponse);
    }
}
