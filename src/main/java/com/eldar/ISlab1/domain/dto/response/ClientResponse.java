package com.eldar.ISlab1.domain.dto.response;

import com.eldar.ISlab1.domain.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientResponse {

    private Long id;

    private String name;

    private String login;

    private UserRole role;

    private Boolean isApproved;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
