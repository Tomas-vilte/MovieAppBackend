package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.mapper.MovieDtoMapper;
import com.peliculas.peliculasapp.application.mapper.MovieReviewDtoMapper;
import com.peliculas.peliculasapp.application.mapper.UserDtoMapper;
import com.peliculas.peliculasapp.application.ports.out.MovieReviewRepositoryPort;
import com.peliculas.peliculasapp.domain.dto.MovieDTO;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import com.peliculas.peliculasapp.domain.models.*;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieReviewNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MovieReviewUseCaseImplTest {
    @Mock
    private MovieReviewRepositoryPort movieReviewRepositoryPort;
    @Mock
    private MovieReviewDtoMapper movieReviewDtoMapper;
    private MovieReviewUseCaseImpl movieReviewUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        movieReviewUseCase = new MovieReviewUseCaseImpl(movieReviewDtoMapper, movieReviewRepositoryPort);
    }

    @Test
    public void testCreateMovieReview() {
        // Arrange
        MovieReview movieReview = createDummyMovieReview();
        MovieReviewDTO expectedDto = createDummyMovieReviewDTO();
        Mockito.when(movieReviewRepositoryPort.createMovieReview(movieReview)).thenReturn(Optional.of(movieReview));
        Mockito.when(movieReviewDtoMapper.toDto(movieReview)).thenReturn(expectedDto);

        // Act
        Optional<MovieReviewDTO> result = movieReviewUseCase.createMovieReview(movieReview);

        // Assert
        assertTrue(result.isPresent());
        MovieReviewDTO actualDto = result.get();
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getMovie(), actualDto.getMovie());
        assertEquals(expectedDto.getUser(), actualDto.getUser());
        assertEquals(expectedDto.getRating(), actualDto.getRating());
        assertEquals(expectedDto.getReviewText(), actualDto.getReviewText());
        assertEquals(expectedDto.getCreatedAt(), actualDto.getCreatedAt());
        assertEquals(expectedDto.getUpdatedAt(), actualDto.getUpdatedAt());
    }

    @Test
    public void testUpdateMovieReview() {
        // Arrange
        MovieReview movieReview = createDummyMovieReview();
        MovieReviewDTO expectedDto = createDummyMovieReviewDTO();
        Mockito.when(movieReviewRepositoryPort.updateMovieReview(movieReview)).thenReturn(Optional.of(movieReview));
        Mockito.when(movieReviewDtoMapper.toDto(movieReview)).thenReturn(expectedDto);

        // act
        Optional<MovieReviewDTO> result = movieReviewUseCase.updateMovieReview(movieReview);

        // assert
        assertTrue(result.isPresent());
        MovieReviewDTO actualDto = result.get();
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getMovie(), actualDto.getMovie());
        assertEquals(expectedDto.getUser(), actualDto.getUser());
        assertEquals(expectedDto.getRating(), actualDto.getRating());
        assertEquals(expectedDto.getReviewText(), actualDto.getReviewText());
        assertEquals(expectedDto.getCreatedAt(), actualDto.getCreatedAt());
        assertEquals(expectedDto.getUpdatedAt(), actualDto.getUpdatedAt());
    }

    @Test
    public void testFindAllReviewsByMovieId() {
        // Arrange
        long movieId = 1L;
        List<MovieReview> movieReviews = new ArrayList<>();
        movieReviews.add(createDummyMovieReview());
        Mockito.when(movieReviewRepositoryPort.findAllReviewsByMovieId(movieId)).thenReturn(movieReviews);

        // Act
        List<MovieReviewDTO> result = movieReviewUseCase.findAllReviewsByMovieId(movieId);

        // Assert
        assertNotNull(result);
        assertEquals(movieReviews.size(), result.size());
    }

    @Test
    void testDeleteReviewById() {
        // Arrange
        long reviewId = 1L;
        MovieReview movieReview = createDummyMovieReview();
        Mockito.when(movieReviewRepositoryPort.getMovieReviewById(reviewId)).thenReturn(Optional.of(movieReview));

        // Act & Assert
        assertDoesNotThrow(() -> movieReviewUseCase.deleteReviewById(reviewId));

        // Arrange Simular que la reseña no existe
        Mockito.when(movieReviewRepositoryPort.getMovieReviewById(reviewId)).thenReturn(Optional.empty());

        MovieReviewNotFoundException exception = assertThrows(MovieReviewNotFoundException.class, () -> movieReviewUseCase.deleteReviewById(reviewId));
        assertEquals("No se encontró ninguna reseña de película con el ID: " + reviewId, exception.getMessage());

    }

    private Movie createDummyMovie() {
        long id = 1;
        String overview = "Descripcion de la pelicula";
        String status = "Estado de la pelcula";
        List<ProductionCompany> productionCompanies = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<ProductionCountries> productionCountries = new ArrayList<>();
        String title = "dragon ball";
        float voteAverage = 4.5f;
        float voteCount = 1000;
        long revenue = 1000000;
        int budget = 500000;
        String posterPath = "/poster.jpg";
        float popularity = 7.8f;
        String releaseDate = "2024-03-18";

        return new Movie(id, overview, status, productionCompanies, genres, productionCountries, title, voteAverage, voteCount, revenue, budget, popularity, posterPath, releaseDate);
    }

    private MovieReview createDummyMovieReview() {
        User user = new User(1L, "username", "password", "user@example.com",LocalDateTime.now(), LocalDateTime.now());
        Movie movie = createDummyMovie();
        return new MovieReview(
                1,
                1,
                1,
                user,
                movie,
                5,
                "buena pelicula",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private MovieReviewDTO createDummyMovieReviewDTO() {
        UserDTO userDTO = createDummyUserDTO();
        MovieDTO movieDTO = createDummyMovieDTO();
        MovieReviewDTO movieReviewDTO = new MovieReviewDTO();
        return new MovieReviewDTO(1,
        movieDTO,
        userDTO,
        5,
        "Esta muy buena la serie",
        LocalDateTime.now(),
        LocalDateTime.now());
    }

    private UserDTO createDummyUserDTO () {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("testUser");
        userDTO.setEmail("userTest@example.com");
        return userDTO;
    }

    private MovieDTO createDummyMovieDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1);
        movieDTO.setTitle("dragon ball");
        movieDTO.setOverview("goku y vegeta");
        movieDTO.setRelease_date("2024-03-18");
        movieDTO.setPopularity(1000);
        return movieDTO;
    }

}