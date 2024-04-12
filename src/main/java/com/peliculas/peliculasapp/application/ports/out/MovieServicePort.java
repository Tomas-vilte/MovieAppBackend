package com.peliculas.peliculasapp.application.ports.out;
import com.peliculas.peliculasapp.domain.models.Movie;

public interface MovieServicePort {
    Movie getMovieInfoById(long movieId);
}
