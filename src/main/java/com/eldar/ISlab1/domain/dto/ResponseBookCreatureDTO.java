package com.eldar.ISlab1.domain.dto;

import com.eldar.ISlab1.domain.model.BookCreatureType;
import com.eldar.ISlab1.domain.model.CoordinatesEntity;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import com.eldar.ISlab1.domain.model.RingEntity;
import lombok.Data;
import org.eclipse.persistence.jpa.jpql.parser.LocalDateTime;

@Data
public class ResponseBookCreatureDTO {
    private Long id;
    private String name;
    private Integer age;
    private BookCreatureType type;
    private MagicCityEntity city;
    private CoordinatesEntity coordinates;
    private RingEntity ring;
    private Float attackLevel;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String creatorName;
    private String updaterName;
}
