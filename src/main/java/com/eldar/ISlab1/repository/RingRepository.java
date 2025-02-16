package com.eldar.ISlab1.repository;

import com.eldar.ISlab1.domain.model.RingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RingRepository extends JpaRepository<RingEntity, Long> , JpaSpecificationExecutor<RingEntity> {

}
