package com.peliculas.peliculasapp.application.ports.out;

import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.domain.models.Movie;

public interface ExternalServicePort {
    Movie getMovieInfoById(long movieId);
    TvSeries getTvSeriesInfoById(long tvSeriesId);
}
