package com.eldar.ISlab1.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RingResponse {
    public Long id;
    public String name;
    public Integer power;
    public Float weight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
