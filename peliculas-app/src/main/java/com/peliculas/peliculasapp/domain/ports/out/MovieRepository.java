package com.peliculas.peliculasapp.domain.ports.out;

import com.peliculas.peliculasapp.domain.models.Movie;

public interface MovieRepository {
    void saveMovieInfo(Movie movie);
}
