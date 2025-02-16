package com.eldar.ISlab1.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RingRequest {

    private Long id;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;

    @NotNull(message = "Power cannot be null")
    @Positive(message = "Power must be greater than 0")
    private Integer power;

    @Positive(message = "Weight must be greater than 0")
    private Float weight;
}
