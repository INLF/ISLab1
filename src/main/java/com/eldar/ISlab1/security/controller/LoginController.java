package com.eldar.ISlab1.security.controller;

import com.eldar.ISlab1.domain.dto.request.LoginRequest;
import com.eldar.ISlab1.domain.dto.response.ClientResponse;
import com.eldar.ISlab1.domain.dto.response.LoginResponse;
import com.eldar.ISlab1.service.ClientService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    private final ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<ClientResponse> loginClient(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
        String token = clientService.generateJwt(loginRequest);
        if (token == null) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        // dev
        //cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        ClientResponse clientResponse = clientService.getClientIfLoggedIn(loginRequest);
        if (clientResponse == null) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(clientResponse);
    }
}
