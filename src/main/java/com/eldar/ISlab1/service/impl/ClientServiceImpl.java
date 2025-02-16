package com.eldar.ISlab1.service.impl;

import com.eldar.ISlab1.domain.auth.ClientPrincipal;
import com.eldar.ISlab1.domain.dto.request.ClientRequest;
import com.eldar.ISlab1.domain.dto.request.LoginRequest;
import com.eldar.ISlab1.domain.dto.response.ClientResponse;
import com.eldar.ISlab1.domain.dto.response.LoginResponse;
import com.eldar.ISlab1.domain.mappers.ClientMapper;
import com.eldar.ISlab1.domain.mappers.ClientMapperImpl;
import com.eldar.ISlab1.domain.model.ClientEntity;
import com.eldar.ISlab1.domain.model.UserRole;
import com.eldar.ISlab1.repository.ClientRepository;
import com.eldar.ISlab1.service.ClientService;
import com.eldar.ISlab1.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository repository;
    private final ClientMapperImpl mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest client) {

        if( repository.existsByLogin(client.getLogin()) )
            throw new IllegalArgumentException("Login already exists");

        ClientEntity clientEntity = mapper.toEntity(client);
        clientEntity.setPassword(passwordEncoder.encode(client.getPassword()));
        clientEntity.setRole(UserRole.USER);
        clientEntity.setIsApproved(true);
        repository.save(clientEntity);

        return mapper.toResponse(repository.save(clientEntity));
    }

    @Override
    public boolean deleteClient(long id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public ClientResponse getClientById(long id) {
        ClientEntity clientEntity = repository.getReferenceById(id);
        return clientMapper.toResponse(clientEntity);
    }

    @Override
    public ClientResponse updateClientById(ClientRequest client) {
        ClientEntity clientEntity = mapper.toEntity(client);
        return clientMapper.toResponse(repository.save(clientEntity));
    }

    @Override
    @Transactional
    public ClientResponse getClientIfLoggedIn(LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        if(authentication.isAuthenticated()) {
            var principal = (ClientPrincipal) authentication.getPrincipal();
            return getClientById(principal.getId());
        }
        return null;
    }

    @Override
    public String generateJwt(LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        if(authentication.isAuthenticated()) {
            return JwtUtil.generateToken(request.getLogin());
        }
        return null;
    }

}
