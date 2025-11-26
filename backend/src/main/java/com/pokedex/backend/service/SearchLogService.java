package com.pokedex.backend.service;

import com.pokedex.backend.entity.SearchLog;
import com.pokedex.backend.repository.SearchLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SearchLogService {
    private final SearchLogRepository searchLogRepository;

    public void log(String pokemonName, SearchLog.Source source, int responseCode, Instant start) {
        SearchLog log = new SearchLog();
        log.setPokemonName(pokemonName);
        log.setSource(source);
        log.setResponseCode(responseCode);
        log.setLatencyMs(latencyMs(start));
        searchLogRepository.save(log);
    }

    private int latencyMs(Instant start) {
        return (int) java.time.Duration.between(start, Instant.now()).toMillis();
    }
}
