package com.pokedex.backend.service;

import com.pokedex.backend.dto.PokemonDTO;
import com.pokedex.backend.entity.Favourite;
import com.pokedex.backend.entity.SearchLog;
import com.pokedex.backend.mapper.PokemonMapper;
import com.pokedex.backend.repository.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {
    private final VendorService vendorService;
    private final CacheService cacheService;
    private final SearchLogService searchLogService;
    private final FavouriteRepository favouriteRepository;
    private final PokemonMapper pokemonMapper;

    @Override
    @Cacheable(value = "pokemon", key = "#name.toLowerCase()")
    public PokemonDTO getPokemon(String name, boolean full) {
        String normalizedName = normalizeName(name);
        Instant start = Instant.now();

        try {
            PokemonDTO pokemon = cacheService.get(normalizedName);
            boolean fromCache = pokemon != null;

            if (!fromCache) {
                pokemon = pokemonMapper.toPokemonDTO(
                        vendorService.fetchPokemon(normalizedName), full, false
                );
                cacheService.put(normalizedName, pokemon);
            }

            searchLogService.log(normalizedName,
                    fromCache ? SearchLog.Source.CACHE : SearchLog.Source.API,
                    HttpStatus.OK.value(), start
            );

            return pokemon;

        } catch (RuntimeException e) {
            searchLogService.log(normalizedName, SearchLog.Source.API,
                    e.getMessage().contains("not found") ?
                            HttpStatus.NOT_FOUND.value() : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    start
            );
            throw e;
        }
    }

    @Override
    public List<PokemonDTO> getAllPokemon() {
        return List.of();
    }

    @Override
    public List<PokemonDTO> searchPokemon(String query) {
        return List.of();
    }

    @Override
    public void addFavourite(String pokemonName) {
        String name = normalizeName(pokemonName);
        if (!favouriteRepository.existsByPokemonName(name)) {
            Favourite favourite = new Favourite();
            favourite.setPokemonName(name);
            favouriteRepository.save(favourite);
        }
    }

    @Override
    public List<String> getFavourites() {
        return favouriteRepository.findAllByOrderByAddedAtDesc()
                .stream()
                .map(Favourite::getPokemonName)
                .toList();
    }

    @Override
    public void removeFavourite(String pokemonName) {
        favouriteRepository.findByPokemonName(normalizeName(pokemonName))
                .ifPresent(favouriteRepository::delete);
    }

    private String normalizeName(String name) {
        return name.trim().toLowerCase().replaceAll("[^a-z0-9-]", "");
    }
}