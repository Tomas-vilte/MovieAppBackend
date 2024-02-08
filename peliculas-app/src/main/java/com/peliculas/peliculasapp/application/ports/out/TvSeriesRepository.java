package com.peliculas.peliculasapp.application.ports.out;
import com.peliculas.peliculasapp.domain.models.TvSeries;

import java.util.Optional;

public interface TvSeriesRepository {
    void saveTvSeriesInfo(TvSeries tvSeries);
    Optional<TvSeries> getTvSeriesById(long seriesId);
}
