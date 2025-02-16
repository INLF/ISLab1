package com.eldar.ISlab1.repository;

import com.eldar.ISlab1.domain.model.BookCreatureType;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import com.eldar.ISlab1.domain.model.RingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicCityRepository extends JpaRepository<MagicCityEntity, Long> , JpaSpecificationExecutor<MagicCityEntity> {
    void deleteAllByGovernorType(BookCreatureType type);
}
