package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.MovieReviewUseCase;
import com.peliculas.peliculasapp.domain.dto.MovieDTO;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import com.peliculas.peliculasapp.domain.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MovieReviewServiceTest {
    private MovieReviewUseCase movieReviewUseCase;
    private MovieReviewService movieReviewService;

    @BeforeEach
    public void setUp() {
        movieReviewUseCase = Mockito.mock(MovieReviewUseCase.class);
        movieReviewService = new MovieReviewService(movieReviewUseCase);
    }

    @Test
    public void testCreateMovieReview() {
        // arrange
        MovieReview movieReview = createDummyMovieReview();
        MovieReviewDTO expectedDto = createDummyMovieReviewDTO();
        Mockito.when(movieReviewService.createMovieReview(movieReview)).thenReturn(Optional.of(expectedDto));

        // act
        Optional<MovieReviewDTO> result = movieReviewService.createMovieReview(movieReview);

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
    public void testFindReviewsByMovieId() {
        // arrange
        long movieId = 1;
        List<MovieReviewDTO> expectedReviews = new ArrayList<>();
        expectedReviews.add(createDummyMovieReviewDTO());
        Mockito.when(movieReviewUseCase.findAllReviewsByMovieId(movieId)).thenReturn(expectedReviews);

        // act
        List<MovieReviewDTO> result = movieReviewService.findReviewsByMovieId(movieId);

        // assert
        assertNotNull(result);
        assertEquals(expectedReviews.size(), result.size());
        assertEquals(expectedReviews, result);
    }

    @Test
    public void updateMovieReview() {
        // arrange
        MovieReview movieReview = createDummyMovieReview();
        MovieReviewDTO expectedDto = createDummyMovieReviewDTO();
        Mockito.when(movieReviewUseCase.updateMovieReview(movieReview)).thenReturn(Optional.of(expectedDto));

        // act
        Optional<MovieReviewDTO> result = movieReviewService.updateMovieReview(movieReview);

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
    public void deleteReviewById() {
        // arrange
        long reviewId = 1;

        // act y assert
        assertDoesNotThrow(() -> movieReviewService.deleteReviewById(reviewId));
        Mockito.verify(movieReviewUseCase, Mockito.times(1)).deleteReviewById(reviewId);
    }

    private MovieReview createDummyMovieReview() {
        User user = new User(1L, "username", "password", "user@example.com");
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