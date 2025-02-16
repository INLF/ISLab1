package com.eldar.ISlab1.service;

import com.eldar.ISlab1.domain.dto.request.BookCreatureRequest;
import com.eldar.ISlab1.domain.dto.request.MagicCityRequest;
import com.eldar.ISlab1.domain.dto.response.BookCreatureResponse;
import com.eldar.ISlab1.domain.dto.response.MagicCityResponse;
import com.eldar.ISlab1.domain.model.BookCreatureEntity;
import com.eldar.ISlab1.domain.model.BookCreatureType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookCreatureService {
    BookCreatureResponse createBookCreature(BookCreatureRequest bookCreatureRequest);
    boolean deleteBookCreature(long id);
    BookCreatureResponse getBookCreatureEntityById(long id);
    BookCreatureResponse updateBookCreature(BookCreatureRequest bookCreatureRequest);
    Page<BookCreatureResponse> getFilteredBook(BookCreatureRequest filter, Pageable pageable);
    void removeOneByAttackLevel(Float attackLevel);
    int countByRing(Long ringId);
    List<Float> getUniqueAttackLevels();
    public void removeRingsFromHobbits();

}
