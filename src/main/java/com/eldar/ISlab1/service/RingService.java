package com.eldar.ISlab1.service;

import com.eldar.ISlab1.domain.dto.request.RingRequest;
import com.eldar.ISlab1.domain.dto.response.RingResponse;
import com.eldar.ISlab1.domain.model.RingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RingService {
    RingResponse createRing(RingRequest request);
    boolean deleteRing(long id);
    RingResponse getRingById(long id);
    RingResponse updateRing(RingRequest request);
    Page<RingResponse> getFilteredRings(RingRequest filter, Pageable pageable);
}
