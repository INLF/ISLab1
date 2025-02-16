package com.eldar.ISlab1.service.impl;

import com.eldar.ISlab1.domain.dto.request.RingRequest;
import com.eldar.ISlab1.domain.dto.response.RingResponse;
import com.eldar.ISlab1.domain.mappers.RingMapper;
import com.eldar.ISlab1.domain.model.ClientEntity;
import com.eldar.ISlab1.domain.model.RingEntity;
import com.eldar.ISlab1.domain.model.UserRole;
import com.eldar.ISlab1.repository.RingRepository;
import com.eldar.ISlab1.repository.specs.RingSpecification;
import com.eldar.ISlab1.service.ClientService;
import com.eldar.ISlab1.service.RingService;
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
public class RingServiceImpl implements RingService {
    private final RingRepository repository;
    private final RingMapper ringMapper;
    private final RingRepository ringRepository;
    private final WebSocketController webSocketController;

    @Override
    public RingResponse createRing(RingRequest request) {
        try {
            RingEntity ringEntity = ringMapper.toEntity(request);
            ringEntity.setCreator(SecurityUtils.getCurrentClientAuthentication());

            ringEntity = repository.save(ringEntity);
            webSocketController.sendUpdate("update");
            return ringMapper.toResponse(ringEntity);
        } catch (Exception e) {
            log.error("Error creating ring: ", e);
            throw new RuntimeException("Error creating ring", e);
        }
    }

    @Override
    public boolean deleteRing(long id) {
        try {
            ClientEntity currentClient = SecurityUtils.getCurrentClientAuthentication();
            RingEntity ringToDelete = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ring with ID " + id + " not found"));

            if (currentClient == null) {
                throw new AccessDeniedException("Client not authenticated");
            }

            if (currentClient.getRole() != UserRole.ADMIN && !currentClient.getId().equals(ringToDelete.getCreator().getId())) {
                throw new AccessDeniedException(
                        String.format("Client with ID %d is not allowed to delete ring created by ID %d",
                                currentClient.getId(),
                                ringToDelete.getCreator().getId()
                        )
                );
            }

            repository.deleteById(id);
            webSocketController.sendUpdate("update");
            return true;
        } catch (Exception e) {
            log.error("Error deleting ring with id {}: ", id, e);
            return false;
        }
    }

    @Override
    public RingResponse getRingById(long id) {
        try {
            RingEntity ringEntity = repository.getReferenceById(id);
            return ringMapper.toResponse(ringEntity);
        } catch (Exception e) {
            log.error("Error retrieving ring with id {}: ", id, e);
            throw new RuntimeException("Error retrieving ring", e);
        }
    }

    @Override
    public RingResponse updateRing(RingRequest request) {
        try {
            ClientEntity currentClient = SecurityUtils.getCurrentClientAuthentication();
            RingEntity ringToUpdate = repository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Ring with ID " + request.getId() + " not found"));

            if (currentClient == null) {
                throw new AccessDeniedException("Client not authenticated");
            }

            if (currentClient.getRole() != UserRole.ADMIN && !currentClient.getId().equals(ringToUpdate.getCreator().getId())) {
                throw new AccessDeniedException(
                        String.format("Client with ID %d is not allowed to update ring created by ID %d",
                                currentClient.getId(),
                                ringToUpdate.getCreator().getId()
                        )
                );
            }

            ringToUpdate.setName(request.getName());
            ringToUpdate.setPower(request.getPower());
            ringToUpdate.setWeight(request.getWeight());

            RingEntity updatedRing = repository.save(ringToUpdate);
            webSocketController.sendUpdate(updatedRing.getId().toString());
            return ringMapper.toResponse(updatedRing);
        } catch (Exception e) {
            log.error("Error updating ring: ", e);
            throw new RuntimeException("Error updating ring", e);
        }
    }

    public Page<RingResponse> getFilteredRings(RingRequest filter, Pageable pageable) {
        RingSpecification ringSpecification = new RingSpecification(
                filter.getName(),
                filter.getPower(),
                filter.getWeight()
        );

        return ringRepository
                .findAll(ringSpecification, pageable)
                .map(ringMapper::toResponse);
    }
}
