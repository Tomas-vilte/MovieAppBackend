package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.infrastructure.entities.MovieEntity;
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
    public void findById(Movie movie) {
        MovieEntity externalMovieEntity = MovieEntity.fromDomainModel(movie);
        MovieEntity saveMovieEntity = movieRepositoryJpa.save(externalMovieEntity);
        saveMovieEntity.toDomainModel();
    }

    @Override
    public Optional<Movie> findById(long movieId) {
        return movieRepositoryJpa.findById(movieId).map(MovieEntity::toDomainModel);
    }
}
