package com.eldar.ISlab1.repository;

import com.eldar.ISlab1.domain.model.BookCreatureEntity;
import com.eldar.ISlab1.domain.model.BookCreatureType;
import com.eldar.ISlab1.domain.model.MagicCityEntity;
import com.eldar.ISlab1.domain.model.RingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookCreatureRepository extends JpaRepository<BookCreatureEntity, Long>, JpaSpecificationExecutor<BookCreatureEntity> {
    Optional<BookCreatureEntity> findFirstByAttackLevel(Float attackLevel);
    int countByRingId(Long ringId);
    // cringe with attackLevel being float
    @Query("SELECT DISTINCT CAST(b.attackLevel AS Integer) FROM BookCreatureEntity b")
    List<Float> findDistinctAttackLevels();
    List<BookCreatureEntity> findByType(BookCreatureType type);
}
