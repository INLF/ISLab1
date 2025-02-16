package com.eldar.ISlab1.controller;


import com.eldar.ISlab1.domain.auth.ClientPrincipal;
import com.eldar.ISlab1.domain.dto.response.ClientResponse;
import com.eldar.ISlab1.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final ClientService clientService;

    @GetMapping("/me")
    public ResponseEntity<ClientResponse> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (authentication.getPrincipal() instanceof ClientPrincipal clientPrincipal) {
            ClientResponse clientResponse = clientService.getClientById(clientPrincipal.getId());
            return ResponseEntity.ok(clientResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
