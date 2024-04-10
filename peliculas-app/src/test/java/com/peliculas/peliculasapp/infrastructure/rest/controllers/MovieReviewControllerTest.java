package com.peliculas.peliculasapp.infrastructure.rest.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peliculas.peliculasapp.application.services.MovieReviewService;
import com.peliculas.peliculasapp.domain.dto.MovieDTO;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import com.peliculas.peliculasapp.domain.dto.UserDTO;
import com.peliculas.peliculasapp.domain.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDateTime;
import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(MovieReviewController.class)
@AutoConfigureMockMvc
class MovieReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MovieReviewService movieReviewService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new MovieReviewController(movieReviewService)).build();
    }


    @Test
    public void testCreateMovieReview() throws Exception {
        // Arrange
        MovieReview movieReview = new MovieReview();
        movieReview.setMovieId(1L);
        movieReview.setUserId(1L);
        movieReview.setRating(5);
        movieReview.setReviewText("¡Una excelente película!");

        MovieReviewDTO expectedDTO = createDummyMovieReviewDTO(29L, "¡Una excelente película!");
        Mockito.when(movieReviewService.createMovieReview(movieReview)).thenReturn(Optional.of(expectedDTO));

        // act y assert
        mockMvc.perform(post("/api/reviews/movies/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"movie_id\": 1, \"user_id\": 1, \"rating\": 5, \"review_text\": \"¡Una excelente película!\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Review de pelicula creada con exito"))
                .andExpect(jsonPath("$.data.id").value(29))
                .andExpect(jsonPath("$.data.movie.id").value(1))
                .andExpect(jsonPath("$.data.movie.title").value("dragon ball"))
                .andExpect(jsonPath("$.data.movie.overview").value("goku y vegeta"))
                .andExpect(jsonPath("$.data.movie.popularity").value(1000))
                .andExpect(jsonPath("$.data.user.id").value(1))
                .andExpect(jsonPath("$.data.user.username").value("testUser"))
                .andExpect(jsonPath("$.data.user.email").value("userTest@example.com"))
                .andExpect(jsonPath("$.data.rating").value(10))
                .andExpect(jsonPath("$.data.review_text").value("¡Una excelente película!"))
                .andExpect(jsonPath("$.data.created_at").exists())
                .andExpect(jsonPath("$.data.updated_at").doesNotExist());
    }

    @Test
    public void testGetMovieReviewById() throws Exception {
        // Arrange
        long movieId = 1L;
        List<MovieReviewDTO> reviewList = new ArrayList<>();
        MovieReviewDTO reviewDTO = createDummyMovieReviewDTO(1, "Buena peli");
        reviewDTO.setUpdatedAt(LocalDateTime.now());
        reviewList.add(reviewDTO);
        Mockito.when(movieReviewService.findReviewsByMovieId(movieId)).thenReturn(reviewList);

        // act y assert
        mockMvc.perform(get("/api/reviews/movies/review/{movieId}", movieId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Review obtenida con exito"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].movie.title").value("dragon ball"))
                .andExpect(jsonPath("$.data[0].movie.overview").value("goku y vegeta"))
                .andExpect(jsonPath("$.data[0].movie.popularity").value(1000))
                .andExpect(jsonPath("$.data[0].user.id").value(1))
                .andExpect(jsonPath("$.data[0].user.username").value("testUser"))
                .andExpect(jsonPath("$.data[0].user.email").value("userTest@example.com"))
                .andExpect(jsonPath("$.data[0].rating").value(10))
                .andExpect(jsonPath("$.data[0].review_text").value("Buena peli"))
                .andExpect(jsonPath("$.data[0].created_at").exists())
                .andExpect(jsonPath("$.data[0].updated_at").exists());
    }

    @Test
    public void testDeleteReviewById() throws Exception {
        // Arrange
        long reviewId = 1L;

        // act y assert
        mockMvc.perform(delete("/api/reviews/movies/review/{reviewId}", reviewId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Review eliminada con exito"))
                .andExpect(jsonPath("$.data").value("Se elimino la review"));
    }

    @Test
    public void testUpdateMovieReview() throws Exception {
        long reviewId = 1L;
        Map<String, Object> reviewData = new HashMap<>();
        reviewData.put("rating", 10);
        reviewData.put("review_text", "Buena peli");

        MovieReviewDTO movieReviewDTO = createDummyMovieReviewDTO(1, "Buena peli");
        Mockito.when(movieReviewService.updateMovieReview(any(MovieReview.class))).thenReturn(Optional.of(movieReviewDTO));

        // Act and Assert
        mockMvc.perform(put("/api/reviews/movies/review/{reviewId}", reviewId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("Review actualizada con exito"))
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.movie.id").value(1))
                .andExpect(jsonPath("$.data.movie.title").value("dragon ball"))
                .andExpect(jsonPath("$.data.movie.overview").value("goku y vegeta"))
                .andExpect(jsonPath("$.data.movie.popularity").value(1000))
                .andExpect(jsonPath("$.data.user.id").value(1))
                .andExpect(jsonPath("$.data.user.username").value("testUser"))
                .andExpect(jsonPath("$.data.user.email").value("userTest@example.com"))
                .andExpect(jsonPath("$.data.rating").value(10))
                .andExpect(jsonPath("$.data.review_text").value("Buena peli"))
                .andExpect(jsonPath("$.data.created_at").exists())
                .andExpect(jsonPath("$.data.updated_at").doesNotExist());
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

    private MovieReviewDTO createDummyMovieReviewDTO(long id, String reviewText) {
        MovieReviewDTO reviewDTO = new MovieReviewDTO();
        reviewDTO.setId(id);

        MovieDTO movieDTO = createDummyMovieDTO();
        reviewDTO.setMovie(movieDTO);

        UserDTO userDTO = createDummyUserDTO();
        reviewDTO.setUser(userDTO);

        reviewDTO.setRating(10);
        reviewDTO.setReviewText(reviewText);
        reviewDTO.setCreatedAt(LocalDateTime.parse("2024-03-27T13:06:31.300415"));

        return reviewDTO;
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

    private UserDTO createDummyUserDTO () {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("testUser");
        userDTO.setEmail("userTest@example.com");
        return userDTO;
    }

    private MovieReview createDummyMovieReview() {
        MovieReview movieReview = new MovieReview();
        User user = new User(1, "username", "password", "user@example.com");
        Movie movie = createDummyMovie();
        return new MovieReview(
                1,
                1,
                1,
                user,
                movie,
                5,
                "buena pelicula",
                LocalDateTime.parse("2024-03-27T13:06:31.300415"),
                LocalDateTime.parse("2024-04-09T15:00:45.749636")
        );
    }
}