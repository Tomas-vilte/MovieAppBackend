package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.infrastructure.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.exceptions.MovieAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class MovieRepositoryJpaImpl implements MovieRepositoryPort {
    private final MovieRepository movieRepositoryJpa;

    @Autowired
    public MovieRepositoryJpaImpl(MovieRepository movieRepository) {
        this.movieRepositoryJpa = movieRepository;
    }

    @Override
    public Optional<Movie> saveMovie(Movie movie) {
        if (movieRepositoryJpa.existsByMovieId(movie.getId())) {
            throw new MovieAlreadyExistsException("Esta película ya se encuentra guardada");
        }
        MovieEntity externalMovieEntity = MovieEntity.fromDomainModel(movie);
        MovieEntity savedMovieEntity = movieRepositoryJpa.save(externalMovieEntity);
        return savedMovieEntity.toDomainModel();
    }
}
