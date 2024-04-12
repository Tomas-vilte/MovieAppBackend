package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.mapper.MovieEntityMapper;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieAlreadyExistsException;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MovieRepositoryJpaImpl implements MovieRepositoryPort {
    private final MovieRepository movieRepositoryJpa;
    private final MovieEntityMapper movieEntityMapper;

    @Autowired
    public MovieRepositoryJpaImpl(MovieRepository movieRepository, MovieEntityMapper movieEntityMapper) {
        this.movieRepositoryJpa = movieRepository;
        this.movieEntityMapper = movieEntityMapper;
    }

    @Override
    public Optional<Movie> saveMovie(Movie movie) {
        try {
            if (movieRepositoryJpa.existsByMovieId(movie.getId())) {
                throw new MovieAlreadyExistsException("Esta película ya se encuentra guardada");
            }
            MovieEntity externalMovieEntity = movieEntityMapper.fromDomainModel(movie);
            MovieEntity savedMovieEntity = movieRepositoryJpa.save(externalMovieEntity);
            return Optional.of(movieEntityMapper.toDomainModel(savedMovieEntity));
        } catch (MovieAlreadyExistsException e) {
            throw new MovieAlreadyExistsException("Esta película ya se encuentra guardada");
        }
    }

    @Override
    public Optional<Movie> getMovieById(long id) {
        Optional<MovieEntity> movieEntityOptional = movieRepositoryJpa.findById(id);
        MovieEntity movieEntity = movieEntityOptional.orElseThrow(() -> new MovieNotFoundException("No se encontró la película con el ID: " + id));
        return Optional.of(movieEntityMapper.toDomainModel(movieEntity));
    }
}