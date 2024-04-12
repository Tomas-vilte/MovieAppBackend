package com.peliculas.peliculasapp.infrastructure.rest.controllers;
import com.peliculas.peliculasapp.application.services.MovieService;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import com.peliculas.peliculasapp.domain.dto.MovieDTO;
import com.peliculas.peliculasapp.domain.dto.MovieInfoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
@ExtendWith(MockitoExtension.class)
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new MovieController(movieService)).build();
    }

    @Test
    void testGetAndSaveMovieInfo() throws Exception {
        // arrange
        long movieId = 6;
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(6);
        movieDTO.setTitle("Interstellar");
        movieDTO.setOverview("A movie about space exploration.");
        movieDTO.setRelease_date("2014-11-07");
        movieDTO.setPopularity(8.7f);
        Mockito.when(movieService.saveMovieInfo(movieId)).thenReturn(movieDTO);

        // act y assert
        mockMvc.perform(post("/api/movies/{id}", movieId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("Pelicula guardada con exito en la base de datos"))
                .andExpect(jsonPath("$.data.id").value(6))
                .andExpect(jsonPath("$.data.title").value("Interstellar"))
                .andExpect(jsonPath("$.data.overview").value("A movie about space exploration."))
                .andExpect(jsonPath("$.data.release_date").value("2014-11-07"))
                .andExpect(jsonPath("$.data.popularity").value(8.7f));
    }

    @Test
    void testGetMovieInfoById() throws Exception {
        // arrange
        long movieId = 9;
        MovieInfoDTO movieInfoDTO = createSampleMovieInfoDTO();
        Mockito.when(movieService.getMovieById(movieId)).thenReturn(movieInfoDTO);

        // Act y Assert
        mockMvc.perform(get("/api/movies/{movieId}", movieId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("Pelicula obtenida con exito"))
                .andExpect(jsonPath("$.data.title").value("Inception"))
                .andExpect(jsonPath("$.data.homepage").value("https://www.warnerbros.com/movies/inception/"))
                .andExpect(jsonPath("$.data.overview").value("Inception is a 2010 science fiction action film written and directed by Christopher Nolan, who also produced the film with Emma Thomas, his wife."))
                .andExpect(jsonPath("$.data.release_date").value("2010-07-16"))
                .andExpect(jsonPath("$.data.popularity").value(42.96))
                .andExpect(jsonPath("$.data.status").value("Released"))
                .andExpect(jsonPath("$.data.production_companies[0].name").value("Warner Bros. Pictures"))
                .andExpect(jsonPath("$.data.production_countries[0].name").value("United States of America"))
                .andExpect(jsonPath("$.data.genres[0].name").value("Action"))
                .andExpect(jsonPath("$.data.vote_average").value(8.8))
                .andExpect(jsonPath("$.data.vote_count").value(29390))
                .andExpect(jsonPath("$.data.revenue").value(829000000))
                .andExpect(jsonPath("$.data.budget").value(160000000))
                .andExpect(jsonPath("$.data.poster_path").value("/poster.jpg"));
    }

    private MovieInfoDTO createSampleMovieInfoDTO() {
        MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
        movieInfoDTO.setTitle("Inception");
        movieInfoDTO.setHomepage("https://www.warnerbros.com/movies/inception/");
        movieInfoDTO.setOverview("Inception is a 2010 science fiction action film written and directed by Christopher Nolan, who also produced the film with Emma Thomas, his wife.");
        movieInfoDTO.setRelease_date("2010-07-16");
        movieInfoDTO.setPopularity(42.96f);
        movieInfoDTO.setStatus("Released");

        List<ProductionCompany> productionCompanies = new ArrayList<>();
        ProductionCompany productionCompany = new ProductionCompany();
        productionCompany.setName("Warner Bros. Pictures");
        productionCompanies.add(productionCompany);
        movieInfoDTO.setProduction_companies(productionCompanies);

        List<ProductionCountries> productionCountries = new ArrayList<>();
        ProductionCountries productionCountry = new ProductionCountries("dad", "23");
        productionCountry.setName("United States of America");
        productionCountries.add(productionCountry);
        movieInfoDTO.setProduction_countries(productionCountries);

        List<Genre> genres = new ArrayList<>();
        Genre genre = new Genre();
        genre.setName("Action");
        genres.add(genre);
        movieInfoDTO.setGenres(genres);

        movieInfoDTO.setVote_average(8.8f);
        movieInfoDTO.setVote_count(29390);
        movieInfoDTO.setRevenue(829000000);
        movieInfoDTO.setBudget(160000000);
        movieInfoDTO.setPoster_path("/poster.jpg");

        return movieInfoDTO;
    }
}