package com.eldar.ISlab1.service.impl;

import com.eldar.ISlab1.domain.auth.ClientPrincipal;
import com.eldar.ISlab1.domain.model.ClientEntity;
import com.eldar.ISlab1.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientDetailsService implements UserDetailsService {
    private final ClientRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientEntity client = repository.findByLogin(username);

        if (client == null) {
            throw new UsernameNotFoundException("Client not found!");
        }

        return new ClientPrincipal(client);
    }
}
