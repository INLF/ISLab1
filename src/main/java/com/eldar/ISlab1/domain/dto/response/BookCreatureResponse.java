package com.eldar.ISlab1.domain.dto.response;

import com.eldar.ISlab1.domain.dto.CoordinatedDto;
import com.eldar.ISlab1.domain.model.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookCreatureResponse {
    private Long id;

    private String name;

    private CoordinatedDto coordinates;

    private Integer age;

    private BookCreatureType type;

    private Long cityId;

    private Long ringId;

    private Float attackLevel;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
