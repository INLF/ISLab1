package com.eldar.ISlab1.repository;

import com.eldar.ISlab1.domain.model.CoordinatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesRepository extends JpaRepository<CoordinatesEntity, Long> {
}
