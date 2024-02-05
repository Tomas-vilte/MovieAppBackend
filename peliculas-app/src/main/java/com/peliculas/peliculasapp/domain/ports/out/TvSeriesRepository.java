package com.peliculas.peliculasapp.domain.ports.out;

import com.peliculas.peliculasapp.domain.models.TvSeries;

public interface TvSeriesRepository {
    void saveTvSeriesInfo(TvSeries tvSeries);
    TvSeries getTvSeriesById(long seriesId);
}
