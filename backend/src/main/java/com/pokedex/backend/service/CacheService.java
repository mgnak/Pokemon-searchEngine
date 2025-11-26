package com.pokedex.backend.service;

import com.pokedex.backend.dto.PokemonDTO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {
    private final Map<String, PokemonDTO> cache = new ConcurrentHashMap<>();

    public PokemonDTO get(String name) {
        return cache.get(name);
    }

    public void put(String name, PokemonDTO pokemon) {
        cache.put(name, pokemon);
    }
}