package com.eldar.ISlab1.security.controller;


import com.eldar.ISlab1.domain.dto.request.ClientRequest;
import com.eldar.ISlab1.domain.dto.request.LoginRequest;
import com.eldar.ISlab1.domain.dto.response.LoginResponse;
import com.eldar.ISlab1.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistrationController {
    private final ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<String> registerClient(@RequestBody @Valid ClientRequest clientRequest) {
        clientService.createClient(clientRequest);
        return ResponseEntity.ok("Client registered successfully");
    }

}
