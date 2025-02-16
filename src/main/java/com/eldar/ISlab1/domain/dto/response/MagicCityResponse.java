package com.eldar.ISlab1.domain.dto.response;

import com.eldar.ISlab1.domain.model.BookCreatureType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MagicCityResponse {
    public Long id;
    public String name;
    public Integer area;
    public Integer population;
    public Integer populationDensity;
    public LocalDate establishmentAt;
    public BookCreatureType governorType;
    public Boolean isCapital;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
