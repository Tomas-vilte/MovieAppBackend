package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.infrastructure.entities.ExternalMovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class MovieRepositoryJpaImpl implements MovieRepositoryPort {

    private JpaRepository<ExternalMovieEntity, Long> MovieRepositoryJpa;

    public MovieRepositoryJpaImpl() {}


    @Override
    public Movie saveMovieInfo(Movie movie) {
        ExternalMovieEntity externalMovieEntity = ExternalMovieEntity.fromDomainModel(movie);
        ExternalMovieEntity saveMovieEntity = MovieRepositoryJpa.save(externalMovieEntity);
        return saveMovieEntity.toDomainModel();
    }

    @Override
    public Optional<Movie> getMovieId(long movieId) {
        return MovieRepositoryJpa.findById(movieId).map(ExternalMovieEntity::toDomainModel);
    }
}
