package com.eldar.ISlab1.service;

import com.eldar.ISlab1.domain.dto.CoordinatedDto;
import com.eldar.ISlab1.domain.model.BookCreatureEntity;
import com.eldar.ISlab1.domain.model.CoordinatesEntity;

public interface CoordinatesService {
    CoordinatedDto createCoordinates(CoordinatedDto coords);
    boolean deleteCoordinates(long id);
    CoordinatedDto getCoordinatesById(long id);
    CoordinatedDto updateCoordinates(CoordinatedDto coords);
}
