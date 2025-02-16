package com.eldar.ISlab1.domain.model;

import com.eldar.ISlab1.domain.converters.BookCreatureTypeConverter;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "magic_city")
public class MagicCityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false )
    private String name;

    private Integer area;
    private Integer population;
    @Column(name = "establishment_at")
    private LocalDate establishmentAt;

    @Column(name = "governor_type", nullable = false)
    private BookCreatureType governorType;
    @Column(name = "population_density")
    private Integer populationDensity;

    @Column(name = "is_capital")
    private Boolean isCapital = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    private ClientEntity creator;

    @ManyToOne
    private ClientEntity updater;

    @PrePersist
    protected void onCreate() {
        var time = LocalDateTime.now();
        this.createdAt = time;
        this.updatedAt = time;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
