package com.peliculas.peliculasapp.application.ports.out;
import com.peliculas.peliculasapp.domain.models.Movie;
import java.util.Optional;

public interface MovieRepositoryPort {
    Optional<Movie> saveMovie(Movie movie);
    
    Optional<Movie> getMovieById(long id);
}
