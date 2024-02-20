package com.peliculas.peliculasapp.application.ports.out;

import com.peliculas.peliculasapp.domain.models.TvSeries;

public interface TvSeriesServicePort {
    TvSeries getTvSeriesInfoById(long id);
}
