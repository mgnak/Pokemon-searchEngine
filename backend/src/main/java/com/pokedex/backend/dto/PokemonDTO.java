package com.pokedex.backend.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PokemonDTO(
        String name,
        Integer id,

        @JsonProperty("base_experience")
        Integer baseExperience,

        Integer height,
        Integer weight,
        List<String> types,
        List<AbilityDTO> abilities,
        List<StatDTO> stats,
        SpriteDTO sprites,
        List<String> moves,

        @JsonProperty("vendor_raw")
        Map<String, Object> vendorRaw,

        @JsonProperty("from_cache")
        boolean fromCache
) {
    public record AbilityDTO(
            String name,

            @JsonProperty("is_hidden")
            Boolean isHidden
    ) {}

    public record StatDTO(
            String name,
            Integer base
    ) {}

    public record SpriteDTO(
            @JsonProperty("front_default")
            String frontDefault,

            @JsonProperty("official_artwork")
            String officialArtwork
    ) {}
}
