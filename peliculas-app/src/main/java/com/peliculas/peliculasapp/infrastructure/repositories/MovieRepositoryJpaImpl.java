package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.infrastructure.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MovieRepositoryJpaImpl implements MovieRepositoryPort {

    private JpaRepository<MovieEntity, Long> MovieRepositoryJpa;

    public MovieRepositoryJpaImpl() {}

    @Override
    public Movie findById(Movie movie) {
        MovieEntity externalMovieEntity = MovieEntity.fromDomainModel(movie);
        MovieEntity saveMovieEntity = MovieRepositoryJpa.save(externalMovieEntity);
        return saveMovieEntity.toDomainModel();
    }

    @Override
    public Optional<Movie> findById(long movieId) {
        return MovieRepositoryJpa.findById(movieId).map(MovieEntity::toDomainModel);
    }
}
