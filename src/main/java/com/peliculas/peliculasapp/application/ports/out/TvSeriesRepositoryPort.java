package com.peliculas.peliculasapp.application.ports.out;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import java.util.Optional;

public interface TvSeriesRepositoryPort {
    Optional<TvSeries> saveTvSeries(TvSeries series);
    Optional<TvSeries> getTvSeriesById(long TvSeriesId);
    Optional<TvSeries> getTvSeriesInfo(long tvSeriesId);
}
