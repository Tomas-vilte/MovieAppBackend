package com.peliculas.peliculasapp.application.ports.out;
import com.peliculas.peliculasapp.domain.models.Movie;
import java.util.Optional;

public interface MovieRepositoryPort {
    Movie findById(Movie movie);
    Optional<Movie> findById(long movieId);
}
