package com.eldar.ISlab1.domain.dto;

import com.eldar.ISlab1.domain.dto.response.RingResponse;
import lombok.Data;

import java.util.List;

@Data
public class RingListDto {
    private Integer pageSize;
    private Integer currentPage;
    private Integer totalPages;
    private Integer totalElements;
    private List<RingResponse> rings;
}
