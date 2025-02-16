package com.eldar.ISlab1.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CoordinatedDto {
    private Long id;
    private Double x;
    private Double y;
}
