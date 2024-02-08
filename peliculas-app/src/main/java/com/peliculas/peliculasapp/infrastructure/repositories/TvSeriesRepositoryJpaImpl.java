package com.peliculas.peliculasapp.infrastructure.repositories;

import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.domain.ports.out.TvSeriesRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TvSeriesRepositoryJpaImpl implements TvSeriesRepository {
    // Implementación pendiente
    // TODO: Implementar métodos de la interfaz TvSeriesRepository
    @Override
    public void saveTvSeriesInfo(TvSeries tvSeries) {

    }

    @Override
    public Optional<TvSeries> getTvSeriesById(long seriesId) {
        return Optional.empty();
    }
}
