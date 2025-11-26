package com.pokedex.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_log")
@Data
public class SearchLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pokemon_name", nullable = false, length = 100)
    private String pokemonName;

    @Column(name = "requested_at", nullable = false)
    private LocalDateTime requestedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Source source;

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "latency_ms")
    private Integer latencyMs;

    public enum Source {
        CACHE, API
    }

    @PrePersist
    protected void onCreate() {
        requestedAt = LocalDateTime.now();
    }
}