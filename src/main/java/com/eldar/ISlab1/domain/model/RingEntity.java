package com.eldar.ISlab1.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "ring")
public class RingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false )
    private String name;

    private Integer power;

    private Float weight;

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
