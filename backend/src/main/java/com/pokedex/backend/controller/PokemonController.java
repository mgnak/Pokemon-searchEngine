package com.pokedex.backend.controller;

import com.pokedex.backend.dto.PokemonDTO;
import com.pokedex.backend.service.PokemonService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PokemonController {
    private final PokemonService pokemonService;

    @GetMapping("/{name}")
    public ResponseEntity<PokemonDTO> getPokemon(
            @PathVariable @NotBlank String name,
            @RequestParam(defaultValue = "false") boolean full) {

        PokemonDTO pokemon = pokemonService.getPokemon(name, full);
        return ResponseEntity.ok()
                .header("X-Cache-Hit", String.valueOf(pokemon.fromCache()))
                .body(pokemon);
    }

    @GetMapping
    public ResponseEntity<List<PokemonDTO>> getAllPokemon() {
        return ResponseEntity.ok(pokemonService.getAllPokemon());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PokemonDTO>> searchPokemon(
            @RequestParam @NotBlank String query) {
        return ResponseEntity.ok(pokemonService.searchPokemon(query));
    }

    @PostMapping("/favourites")
    public ResponseEntity<Void> addFavourite(@RequestBody FavouriteRequest request) {
        pokemonService.addFavourite(request.name());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favourites")
    public ResponseEntity<List<String>> getFavourites() {
        return ResponseEntity.ok(pokemonService.getFavourites());
    }

    @DeleteMapping("/favourites/{name}")
    public ResponseEntity<Void> removeFavourite(@PathVariable @NotBlank String name) {
        pokemonService.removeFavourite(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    public record FavouriteRequest(String name) {}
}