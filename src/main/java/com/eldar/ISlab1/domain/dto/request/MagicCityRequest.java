package com.eldar.ISlab1.domain.dto.request;

import com.eldar.ISlab1.domain.model.BookCreatureType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MagicCityRequest {

    private Long id;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;

    @NotNull(message = "Area cannot be null")
    @Positive(message = "Area must be greater than 0")
    private Long area;

    @Positive(message = "Population must be greater than 0")
    private Long population;

    private LocalDate establishmentAt;

    @NotNull(message = "Governor type cannot be null")
    private BookCreatureType governorType;

    @NotNull(message = "Capital status cannot be null")
    private Boolean isCapital;

    @NotNull(message = "Population density cannot be null")
    @Positive(message = "Population density must be greater than 0")
    private Long populationDensity;
}
