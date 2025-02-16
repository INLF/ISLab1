package com.eldar.ISlab1.service.impl;

import com.eldar.ISlab1.domain.dto.CoordinatedDto;
import com.eldar.ISlab1.domain.dto.request.BookCreatureRequest;
import com.eldar.ISlab1.domain.dto.response.BookCreatureResponse;
import com.eldar.ISlab1.domain.mappers.BookCreatureMapper;
import com.eldar.ISlab1.domain.model.*;
import com.eldar.ISlab1.repository.BookCreatureRepository;
import com.eldar.ISlab1.repository.CoordinatesRepository;
import com.eldar.ISlab1.repository.MagicCityRepository;
import com.eldar.ISlab1.repository.RingRepository;
import com.eldar.ISlab1.repository.specs.BookCreatureSpecification;
import com.eldar.ISlab1.service.BookCreatureService;
import com.eldar.ISlab1.util.SecurityUtils;
import com.eldar.ISlab1.ws.controllers.WebSocketController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookCreatureServiceImpl implements BookCreatureService {
    private final BookCreatureRepository bookCreatureRepository;
    private final BookCreatureMapper bookCreatureMapper;
    private final CoordinatesRepository coordinatesRepository;
    private final MagicCityRepository magicCityRepository;
    private final RingRepository ringRepository;
    private final WebSocketController webSocketController;

    public void removeOneByAttackLevel(Float attackLevel) {
        bookCreatureRepository.findFirstByAttackLevel(attackLevel).ifPresent(bookCreatureRepository::delete);
        webSocketController.sendUpdate("update");
    }

    public int countByRing(Long ringId) {
        int count = bookCreatureRepository.countByRingId(ringId);
        webSocketController.sendUpdate("update");
        return count;
    }

    public List<Float> getUniqueAttackLevels() {
        List<Float> list = bookCreatureRepository.findDistinctAttackLevels();
        webSocketController.sendUpdate("update");
        return list;
    }

    public void removeRingsFromHobbits() {
        List<BookCreatureEntity> hobbits = bookCreatureRepository.findByType(BookCreatureType.HOBBIT);
        hobbits.forEach(hobbit -> hobbit.setRing(null));
        bookCreatureRepository.saveAll(hobbits);
        webSocketController.sendUpdate("update");
    }

    @Transactional
    @Override
    public BookCreatureResponse createBookCreature(BookCreatureRequest request) {
        try {
            BookCreatureEntity bookCreature = bookCreatureMapper.toEntity(request);

            // TODO cascade
            coordinatesRepository.save(bookCreature.getCoordinates());

            if (request.getCityId() != null) {
                MagicCityEntity city = magicCityRepository.findById(request.getCityId())
                        .orElseThrow(() -> new RuntimeException("City with ID " + request.getCityId() + " not found"));
                bookCreature.setCity(city);
            }

            if (request.getRingId() != null) {
                RingEntity ring = ringRepository.findById(request.getRingId())
                        .orElseThrow(() -> new RuntimeException("Ring with ID " + request.getRingId() + " not found"));
                bookCreature.setRing(ring);
            }

            bookCreature.setCreator(SecurityUtils.getCurrentClientAuthentication());
            bookCreature = bookCreatureRepository.save(bookCreature);
            webSocketController.sendUpdate("update");
            return bookCreatureMapper.toResponse(bookCreature);
        } catch (Exception e) {
            log.error("Error saving creature: ", e);
            throw new RuntimeException("Error saving creature", e);
        }
    }
    @Transactional
    @Override
    public boolean deleteBookCreature(long id) {
        try {
            ClientEntity currentClient = SecurityUtils.getCurrentClientAuthentication();
            BookCreatureEntity creatureToDelete = bookCreatureRepository.getReferenceById(id);
            if (currentClient == null) {
                throw new AccessDeniedException("Client with ID " + id + " not found");
            }
            // order matters in some cases we could have ring withour creator
            if (currentClient.getRole() != UserRole.ADMIN && !currentClient.getId().equals(creatureToDelete.getCreator().getId())) {
                throw new AccessDeniedException(
                        String.format("Client with ID %d is not allowed to delete creature created by ID %d",
                                currentClient.getId(),
                                creatureToDelete.getCreator().getId()
                        )
                );
            }

            coordinatesRepository.deleteById(creatureToDelete.getCoordinates().getId());

            bookCreatureRepository.delete(creatureToDelete);
            webSocketController.sendUpdate("update");

            return true;
        } catch (Exception e) {
            log.error("Error deleting creature with id {}: ", id, e);
            return false;
        }
    }
    @Transactional
    @Override
    public BookCreatureResponse getBookCreatureEntityById(long id) {
        try {
            // Fetch the entity from the database
            BookCreatureEntity bookCreature = bookCreatureRepository.getReferenceById(id);

            // Map entity to response DTO
            return bookCreatureMapper.toResponse(bookCreature);
        } catch (Exception e) {
            log.error("Error while getting creature with id {}: ", id, e);
            throw new RuntimeException("Error retrieving creature", e);
        }
    }
    @Transactional
    @Override
    public BookCreatureResponse updateBookCreature(BookCreatureRequest request) {
        try {
            ClientEntity currentClient = SecurityUtils.getCurrentClientAuthentication();
            BookCreatureEntity creatureToUpdate = bookCreatureRepository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("BookCreature with ID " + request.getId() + " not found"));

            if (currentClient == null) {
                throw new AccessDeniedException("Current client authentication not found.");
            }

            if (currentClient.getRole() != UserRole.ADMIN && !currentClient.getId().equals(creatureToUpdate.getCreator().getId())) {
                throw new AccessDeniedException(
                        String.format("Client with ID %d is not allowed to update creature created by ID %d",
                                currentClient.getId(),
                                creatureToUpdate.getCreator().getId()
                        )
                );
            }

            creatureToUpdate.setName(request.getName());
            creatureToUpdate.setAge(request.getAge());
            creatureToUpdate.setType(request.getType());
            creatureToUpdate.setAttackLevel(request.getAttackLevel());

            if (request.getCityId() != null) {
                MagicCityEntity city = magicCityRepository.findById(request.getCityId())
                        .orElseThrow(() -> new RuntimeException("City with ID " + request.getCityId() + " not found"));
                creatureToUpdate.setCity(city);
            }

            if (request.getRingId() != null) {
                RingEntity ring = ringRepository.findById(request.getRingId())
                        .orElseThrow(() -> new RuntimeException("Ring with ID " + request.getRingId() + " not found"));
                creatureToUpdate.setRing(ring);
            }

            BookCreatureEntity updatedEntity = bookCreatureRepository.save(creatureToUpdate);

            log.info("BookCreature with ID {} updated successfully by client ID {}", updatedEntity.getId(), currentClient.getId());
            webSocketController.sendUpdate("update");
            return bookCreatureMapper.toResponse(updatedEntity);
        } catch (Exception e) {
            log.error("Error while updating BookCreature with ID {}: ", request.getId(), e);
            throw new RuntimeException("Error updating BookCreature", e);
        }
    }


    @Override
    public Page<BookCreatureResponse> getFilteredBook(BookCreatureRequest filter, Pageable pageable) {
        BookCreatureSpecification specification =  new BookCreatureSpecification(
                filter.getName(),
                filter.getAge(),
                filter.getType(),
                filter.getCityId(),
                filter.getRingId()
        );

        return bookCreatureRepository
                .findAll(specification, pageable)
                .map(bookCreatureMapper::toResponse);
    }


}
