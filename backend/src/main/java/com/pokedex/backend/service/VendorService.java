package com.pokedex.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class VendorService {
    private final WebClient webClient;

    public JsonNode fetchPokemon(String name) {
        try {
            return webClient.get()
                    .uri("/pokemon/{name}", name)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block(Duration.ofSeconds(5));
        } catch (WebClientResponseException.NotFound e) {
            throw new RuntimeException("Pokemon not found: " + name);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch Pokemon: " + name);
        }
    }
}