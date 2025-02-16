package com.eldar.ISlab1.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "book_creature")
public class BookCreatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    /// fucking delirium wtf I am doing
    @OneToOne
    @JoinColumn(name = "id_coordinates")
    private CoordinatesEntity coordinates;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_type")
    private BookCreatureType type;

    @ManyToOne
    @JoinColumn(name = "id_city")
    private MagicCityEntity city;

    @ManyToOne
    @JoinColumn(name = "id_ring")
    private RingEntity ring;

    @Column(name = "attack_level")
    private Float attackLevel;

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
