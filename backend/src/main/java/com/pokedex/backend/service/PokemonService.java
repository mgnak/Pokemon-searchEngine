package com.pokedex.backend.service;

import com.pokedex.backend.dto.PokemonDTO;
import java.util.List;

public interface PokemonService {
    PokemonDTO getPokemon(String name, boolean full);
    List<PokemonDTO> getAllPokemon();
    List<PokemonDTO> searchPokemon(String query);
    void addFavourite(String pokemonName);
    List<String> getFavourites();
    void removeFavourite(String pokemonName);
}