package com.eldar.ISlab1.service.impl;

import com.eldar.ISlab1.domain.dto.CoordinatedDto;
import com.eldar.ISlab1.domain.mappers.CoordinatesMapper;
import com.eldar.ISlab1.domain.model.CoordinatesEntity;
import com.eldar.ISlab1.repository.CoordinatesRepository;
import com.eldar.ISlab1.service.CoordinatesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CoordinatesServiceImpl implements CoordinatesService {
    private final CoordinatesRepository repository;
    private final CoordinatesMapper coordinatesMapper;

    @Override
    public CoordinatedDto createCoordinates(CoordinatedDto request) {
        try {
            CoordinatesEntity coordinatesEntity = coordinatesMapper.toEntity(request);

            coordinatesEntity = repository.save(coordinatesEntity);

            return coordinatesMapper.toResponse(coordinatesEntity);
        } catch (Exception e) {
            log.error("Error saving coordinates: ", e);
            throw new RuntimeException("Error saving coordinates", e);
        }
    }

    @Override
    public boolean deleteCoordinates(long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting coordinates with id {}: ", id, e);
            return false;
        }
    }

    @Override
    public CoordinatedDto getCoordinatesById(long id) {
        try {
            CoordinatesEntity coordinatesEntity = repository.getReferenceById(id);
            return coordinatesMapper.toResponse(coordinatesEntity);
        } catch (Exception e) {
            log.error("Error retrieving coordinates with id {}: ", id, e);
            throw new RuntimeException("Error retrieving coordinates", e);
        }
    }

    @Override
    public CoordinatedDto updateCoordinates(CoordinatedDto request) {
        try {
            CoordinatesEntity coordinatesEntity = repository.getReferenceById(request.getId());
            coordinatesEntity.setX(request.getX());
            coordinatesEntity.setY(request.getY());

            coordinatesEntity = repository.save(coordinatesEntity);

            return coordinatesMapper.toResponse(coordinatesEntity);
        } catch (Exception e) {
            log.error("Error updating coordinates with id {}: ", request.getId(), e);
            throw new RuntimeException("Error updating coordinates", e);
        }
    }
}
