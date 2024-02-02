package com.peliculas.peliculasapp.domain.ports.out;

import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.TvSeries;

public interface ExternalServicePort {
    Movie getMovieInfoById(long movieId);
    TvSeries getTvSeriesInfoById(long tvSeriesId);
}
