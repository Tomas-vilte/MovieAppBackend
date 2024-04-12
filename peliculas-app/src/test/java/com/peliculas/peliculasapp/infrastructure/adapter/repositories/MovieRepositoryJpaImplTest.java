package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieAlreadyExistsException;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieNotFoundException;
import com.peliculas.peliculasapp.infrastructure.adapter.mapper.MovieEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieRepositoryJpaImplTest {
    @Mock
    private MovieRepository movieRepositoryMock;
    @Mock
    private MovieEntityMapper movieEntityMapperMock;
    @InjectMocks
    private MovieRepositoryJpaImpl movieRepositoryJpa;
    private Movie movie;
    private MovieEntity movieEntity;

    @BeforeEach
    public void setUp() {
        movie = createMovie(1L, "Movie Title");
        movieEntity = createMovieEntity(1L, "Movie Title");
    }

    public Movie createMovie(Long id, String title) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        return movie;
    }

    public MovieEntity createMovieEntity(Long id, String title) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(id);
        movieEntity.setTitle(title);
        return movieEntity;
    }

    @Test
    public void saveMovieSuccess() {
        // arrange
        Mockito.when(movieRepositoryMock.existsByMovieId(1L)).thenReturn(false);
        Mockito.when(movieEntityMapperMock.fromDomainModel(movie)).thenReturn(movieEntity);
        Mockito.when(movieRepositoryMock.save(movieEntity)).thenReturn(movieEntity);
        Mockito.when(movieEntityMapperMock.toDomainModel(movieEntity)).thenReturn(movie);

        // act
        Optional<Movie> savedMovie = movieRepositoryJpa.saveMovie(movie);

        // assert
        assertTrue(savedMovie.isPresent());
        assertEquals(movie.getId(), savedMovie.get().getId());
        assertEquals(movie.getTitle(), savedMovie.get().getTitle());

        Mockito.verify(movieRepositoryMock, Mockito.times(1)).existsByMovieId(1L);
        Mockito.verify(movieEntityMapperMock, Mockito.times(1)).fromDomainModel(movie);
        Mockito.verify(movieRepositoryMock, Mockito.times(1)).save(movieEntity);
        Mockito.verify(movieEntityMapperMock, Mockito.times(1)).toDomainModel(movieEntity);
    }

    @Test
    public void saveMovieMovieAlreadyExists() {
        // arrange
        Mockito.when(movieRepositoryMock.existsByMovieId(1L)).thenReturn(true);

        // act
        Exception exception = assertThrows(MovieAlreadyExistsException.class, () -> {
            movieRepositoryJpa.saveMovie(movie);
        });

        // assert
        assertEquals("Esta película ya se encuentra guardada", exception.getMessage());
    }

    @Test
    public void getMovieByIdSuccess() {
        // arrange
        Mockito.when(movieRepositoryMock.findById(1L)).thenReturn(Optional.of(movieEntity));
        Mockito.when(movieEntityMapperMock.toDomainModel(movieEntity)).thenReturn(movie);

        // act
        Optional<Movie> retrievedMovie = movieRepositoryJpa.getMovieById(1L);

        // assert
        assertTrue(retrievedMovie.isPresent());
        assertEquals(movie.getId(), retrievedMovie.get().getId());
        assertEquals(movie.getTitle(), retrievedMovie.get().getTitle());

        Mockito.verify(movieRepositoryMock).findById(1L);
        Mockito.verify(movieEntityMapperMock).toDomainModel(movieEntity);
    }

    @Test
    public void getMovieByIdMovieNotFound() {
        // arrange
        Mockito.when(movieRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> {
            movieRepositoryJpa.getMovieById(1L);
        });

        Mockito.verify(movieRepositoryMock).findById(1L);
        Mockito.verifyNoInteractions(movieEntityMapperMock);
    }
}