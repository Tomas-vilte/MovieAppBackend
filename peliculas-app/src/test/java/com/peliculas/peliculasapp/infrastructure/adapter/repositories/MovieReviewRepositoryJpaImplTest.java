package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieReviewEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.UserEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieReviewNotFoundException;
import com.peliculas.peliculasapp.infrastructure.adapter.mapper.MovieReviewMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class MovieReviewRepositoryJpaImplTest {
    @Mock
    private MovieReviewRepository movieReviewRepositoryMock;
    @Mock
    private MovieReviewMapper movieReviewMapperMock;
    @Mock
    private MovieRepository movieRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    private MovieReviewRepositoryJpaImpl movieReviewRepositoryJpa;

    private MovieReview movieReview;
    private MovieReviewEntity movieReviewEntity;
    private UserEntity userEntity;
    private MovieEntity movieEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        movieReviewRepositoryJpa = new MovieReviewRepositoryJpaImpl(
                movieReviewRepositoryMock,
                movieReviewMapperMock,
                movieRepositoryMock,
                userRepositoryMock
        );
        Movie movie = createMovie(1L, "elsabalero");
        movieReview = createMovieReview(1L, 1L, 5, "Great movie!", movie);
        movieReviewEntity = createMovieReviewEntity(1L, movieEntity, userEntity, 5, "¡Una película increíble!");
        movieEntity = createMovieEntity(1L, "Movie Title");
        userEntity = createUserEntity(1L, "username", "user@example.com");
    }

    public Movie createMovie(Long id, String title) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        return movie;
    }

    public MovieReview createMovieReview(long movieId, long userId, int rating, String reviewText, Movie movie) {
        MovieReview movieReview = new MovieReview();
        movieReview.setId(1);
        movieReview.setMovieId(movieId);
        movieReview.setMovie(movie);
        movieReview.setUserId(userId);
        movieReview.setRating(rating);
        movieReview.setReviewText(reviewText);
        return movieReview;
    }

    public MovieReviewEntity createMovieReviewEntity(long id, MovieEntity movie, UserEntity user, int rating, String reviewText) {
        MovieReviewEntity movieReviewEntity = new MovieReviewEntity();
        movieReviewEntity.setId(id);
        movieReviewEntity.setMovie(movie);
        movieReviewEntity.setUser(user);
        movieReviewEntity.setRating(rating);
        movieReviewEntity.setReviewText(reviewText);
        return movieReviewEntity;
    }

    public MovieEntity createMovieEntity(long id, String title) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(id);
        movieEntity.setMovieId(1);
        movieEntity.setTitle(title);
        return movieEntity;
    }

    public UserEntity createUserEntity(long id, String username, String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        return userEntity;
    }

    @Test
    public void createMovieReviewSuccess() {
        // arrange
        Mockito.when(movieRepositoryMock.findById(1L)).thenReturn(Optional.of(movieEntity));
        Mockito.when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(userEntity));
        Mockito.when(movieReviewMapperMock.fromDomainModel(movieReview, movieEntity, userEntity)).thenReturn(movieReviewEntity);
        Mockito.when(movieReviewRepositoryMock.save(movieReviewEntity)).thenReturn(movieReviewEntity);
        Mockito.when(movieReviewMapperMock.toDomainModel(movieReviewEntity)).thenReturn(movieReview);

        // act
        Optional<MovieReview> savedMovieReview = movieReviewRepositoryJpa.createMovieReview(movieReview);

        // assert
        assertTrue(savedMovieReview.isPresent());
        assertEquals(movieReview.getId(), savedMovieReview.get().getId());
        assertEquals(movieReview.getMovieId(), savedMovieReview.get().getMovieId());
        assertEquals(movieReview.getUserId(), savedMovieReview.get().getUserId());
        assertEquals(movieReview.getRating(), savedMovieReview.get().getRating());
        assertEquals(movieReview.getReviewText(), savedMovieReview.get().getReviewText());

        assertEquals(movieReview, savedMovieReview.get());
        Mockito.verify(movieReviewRepositoryMock).save(movieReviewEntity);
    }

    @Test
    public void testUpdateMovieReview() {
        // arrange
        MovieReview updatedReview = new MovieReview();
        updatedReview.setId(1);
        updatedReview.setMovieId(1);
        updatedReview.setUserId(1);
        updatedReview.setRating(7);
        updatedReview.setReviewText("Updated review");

        Mockito.when(movieReviewRepositoryMock.findById(movieReview.getId())).thenReturn(Optional.ofNullable(movieReviewEntity));
        Mockito.when(movieReviewRepositoryMock.save(movieReviewEntity)).thenReturn(movieReviewEntity);
        Mockito.when(movieReviewMapperMock.toDomainModel(movieReviewEntity)).thenReturn(updatedReview);

        // act
        Optional<MovieReview> result = movieReviewRepositoryJpa.updateMovieReview(updatedReview);

        // assert
        assertTrue(result.isPresent());
        assertEquals(updatedReview, result.get());
        Mockito.verify(movieReviewRepositoryMock).save(Mockito.any(MovieReviewEntity.class));
    }

    @Test
    public void testUpdateMovieReviewNotFound() {
        // arrange
        MovieReview updatedReview = new MovieReview();
        updatedReview.setId(1);
        updatedReview.setMovieId(1);
        updatedReview.setUserId(1);
        updatedReview.setRating(7);
        updatedReview.setReviewText("Updated review");
        Mockito.when(movieReviewRepositoryMock.findById(movieReview.getId())).thenReturn(Optional.empty());

        // act y assert
        assertThrows(MovieReviewNotFoundException.class, () -> {
            movieReviewRepositoryJpa.updateMovieReview(updatedReview);
        });
    }

    @Test
    void testGetMovieReviewById() {
        // arrange
        Mockito.when(movieReviewRepositoryMock.findById(movieReview.getId())).thenReturn(Optional.of(movieReviewEntity));
        Mockito.when(movieReviewMapperMock.toDomainModel(movieReviewEntity)).thenReturn(movieReview);

        // act
        Optional<MovieReview> result = movieReviewRepositoryJpa.getMovieReviewById(movieReview.getId());

        // assert
        assertTrue(result.isPresent());
        assertEquals(movieReview, result.get());
    }

    @Test
    public void testGetMovieReviewByIdNotFound() {
        Mockito.when(movieReviewRepositoryMock.findById(movieReview.getId())).thenReturn(Optional.empty());

        // act
        Optional<MovieReview> result = movieReviewRepositoryJpa.getMovieReviewById(movieReview.getId());

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAllReviewsByMovieId() {
        // arrange
        long movieId = 1L;
        MovieReviewEntity movieReviewEntity = new MovieReviewEntity();
        movieReviewEntity.setId(1L);
        Mockito.when(movieReviewRepositoryMock.findAllReviewsByMovieId(Mockito.anyLong())).thenReturn(List.of(movieReviewEntity));
        Mockito.when(movieReviewMapperMock.toDomainModel(Mockito.any())).thenReturn(new MovieReview());

        // act
        List<MovieReview> result = movieReviewRepositoryJpa.findAllReviewsByMovieId(movieId);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void testDeleteReviewById() {
        long reviewId = 1L;
        Mockito.when(movieReviewRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(new MovieReviewEntity()));

        assertDoesNotThrow(() -> movieReviewRepositoryJpa.deleteReviewById(reviewId));
    }

    @Test
    public void testDeleteReviewByIdReviewNotFound() {
        long reviewId = 1L;
        Mockito.when(movieReviewRepositoryMock.findById(reviewId)).thenReturn(Optional.empty());

        assertThrows(MovieReviewNotFoundException.class, () -> {
            movieReviewRepositoryJpa.deleteReviewById(reviewId);
        });
    }
}