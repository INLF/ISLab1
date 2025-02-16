package com.eldar.ISlab1.service;

import com.eldar.ISlab1.domain.dto.request.MagicCityRequest;
import com.eldar.ISlab1.domain.dto.request.RingRequest;
import com.eldar.ISlab1.domain.dto.response.MagicCityResponse;
import com.eldar.ISlab1.domain.dto.response.RingResponse;
import com.eldar.ISlab1.domain.model.BookCreatureType;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import com.eldar.ISlab1.domain.model.RingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MagicCityService {
    MagicCityResponse createCity(MagicCityRequest request);
    boolean softDeleteCity(long id);
    MagicCityResponse getCityEntityById(long id);
    MagicCityResponse updateCity(MagicCityRequest request);
    Page<MagicCityResponse> getFilteredCities(MagicCityRequest filter, Pageable pageable);
    void destroyElfCities();
}
