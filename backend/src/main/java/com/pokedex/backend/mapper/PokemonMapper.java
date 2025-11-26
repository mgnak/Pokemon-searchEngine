package com.pokedex.backend.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.pokedex.backend.dto.PokemonDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PokemonMapper {

    public PokemonDTO toPokemonDTO(JsonNode response, boolean full, boolean fromCache) {
        return new PokemonDTO(
                response.get("name").asText(),
                response.get("id").asInt(),
                response.get("base_experience").asInt(),
                response.get("height").asInt(),
                response.get("weight").asInt(),
                extractTypes(response),
                extractAbilities(response),
                extractStats(response),
                extractSprites(response),
                extractMoves(response),
                full ? extractVendorRaw(response) : null,
                fromCache
        );
    }

    private List<String> extractTypes(JsonNode response) {
        List<String> types = new ArrayList<>();
        response.get("types").forEach(type ->
                types.add(type.get("type").get("name").asText())
        );
        return types;
    }

    private List<PokemonDTO.AbilityDTO> extractAbilities(JsonNode response) {
        List<PokemonDTO.AbilityDTO> abilities = new ArrayList<>();
        response.get("abilities").forEach(ability ->
                abilities.add(new PokemonDTO.AbilityDTO(
                        ability.get("ability").get("name").asText(),
                        ability.get("is_hidden").asBoolean()
                ))
        );
        return abilities;
    }

    private List<PokemonDTO.StatDTO> extractStats(JsonNode response) {
        List<PokemonDTO.StatDTO> stats = new ArrayList<>();
        response.get("stats").forEach(stat ->
                stats.add(new PokemonDTO.StatDTO(
                        stat.get("stat").get("name").asText(),
                        stat.get("base_stat").asInt()
                ))
        );
        return stats;
    }

    private PokemonDTO.SpriteDTO extractSprites(JsonNode response) {
        JsonNode sprites = response.get("sprites");
        String frontDefault = getText(sprites, "front_default");
        String officialArtwork = getText(sprites.path("other").path("official-artwork"), "front_default");
        return new PokemonDTO.SpriteDTO(frontDefault, officialArtwork);
    }

    private List<String> extractMoves(JsonNode response) {
        List<String> moves = new ArrayList<>();
        response.get("moves").forEach(move ->
                moves.add(move.get("move").get("name").asText())
        );
        return moves.subList(0, Math.min(moves.size(), 10));
    }

    private Map<String, Object> extractVendorRaw(JsonNode response) {
        return Map.of(
                "id", response.get("id").asInt(),
                "name", response.get("name").asText(),
                "base_experience", response.get("base_experience").asInt(),
                "height", response.get("height").asInt(),
                "weight", response.get("weight").asInt()
        );
    }

    private String getText(JsonNode node, String field) {
        return node.has(field) ? node.get(field).asText() : null;
    }
}