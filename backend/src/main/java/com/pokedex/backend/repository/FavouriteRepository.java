package com.pokedex.backend.repository;

import com.pokedex.backend.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
    boolean existsByPokemonName(String pokemonName);

    Optional<Favourite> findByPokemonName(String pokemonName);

    Optional<Favourite> findAllByOrderByAddedAtDesc();
}
