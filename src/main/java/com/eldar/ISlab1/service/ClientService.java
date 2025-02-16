package com.eldar.ISlab1.service;

import com.eldar.ISlab1.domain.dto.request.ClientRequest;
import com.eldar.ISlab1.domain.dto.request.LoginRequest;
import com.eldar.ISlab1.domain.dto.response.ClientResponse;
import com.eldar.ISlab1.domain.dto.response.LoginResponse;
import com.eldar.ISlab1.domain.model.ClientEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ClientService {
    ClientResponse createClient(ClientRequest clientRequest);
    boolean deleteClient(long id);
    ClientResponse getClientIfLoggedIn(LoginRequest loginRequest);
    String generateJwt(LoginRequest loginRequest);
    ClientResponse getClientById(long id);
    ClientResponse updateClientById(ClientRequest clientRequest);
}
