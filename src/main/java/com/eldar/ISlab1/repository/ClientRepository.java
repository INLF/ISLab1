package com.eldar.ISlab1.repository;

import com.eldar.ISlab1.domain.model.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity , Long> {
    ClientEntity findByLogin(String login);

    boolean existsByLogin(String login);
}
