package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.ports.out.MovieRepository;
import com.peliculas.peliculasapp.infrastructure.entities.ExternalMovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MovieRepositoryJpaImpl implements MovieRepository {

    private final MovieRepositoryJpa movieRepositoryJpa;

    @Autowired
    public MovieRepositoryJpaImpl(MovieRepositoryJpa movieRepositoryJpa) {
        this.movieRepositoryJpa = movieRepositoryJpa;
    }

    @Override
    public Movie saveMovieInfo(Movie movie) {
        ExternalMovieEntity externalMovieEntity = ExternalMovieEntity.fromDomainModel(movie);
        ExternalMovieEntity saveMovieEntity = movieRepositoryJpa.save(externalMovieEntity);
        return saveMovieEntity.toDomainModel();
    }

    @Override
    public Optional<Movie> getMovieId(long movieId) {
        return movieRepositoryJpa.findById(movieId).map(ExternalMovieEntity::toDomainModel);
    }
}
