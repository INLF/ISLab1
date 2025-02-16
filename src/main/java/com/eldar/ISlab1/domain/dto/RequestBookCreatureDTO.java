package com.eldar.ISlab1.domain.dto;

import com.eldar.ISlab1.domain.model.BookCreatureType;
import lombok.Data;

@Data
public class RequestBookCreatureDTO {
    private Integer id;
    private String name;
    private Integer age;
    private BookCreatureType type;
    private Double x;
    private Double y;
    private Long cityId;
    private Long ringId;
    private Float attackLevel;
    private Boolean isActive;
    private Long creatorId;
    private Long updaterId;

}
