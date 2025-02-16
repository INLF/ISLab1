package com.eldar.ISlab1.domain.dto.request;

import com.eldar.ISlab1.domain.dto.CoordinatedDto;
import com.eldar.ISlab1.domain.model.BookCreatureType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreatureRequest {

    @Positive(message = "ID must be greater than 0")
    private Long id;

    @NotBlank(message = "Name cannot be null or empty")
    private String name;

    @NotNull(message = "Coordinates cannot be null")
    private CoordinatedDto coordinates;

    @Positive(message = "Age must be greater than 0")
    private Integer age;

    private BookCreatureType type;

    private Long cityId;

    @NotNull(message = "Ring ID cannot be null")
    private Long ringId;

    @Positive(message = "Attack level must be greater than 0")
    private Float attackLevel;
}
