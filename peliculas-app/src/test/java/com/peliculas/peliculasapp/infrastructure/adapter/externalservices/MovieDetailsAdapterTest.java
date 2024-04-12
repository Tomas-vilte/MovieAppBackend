package com.peliculas.peliculasapp.infrastructure.adapter.externalservices;
import com.peliculas.peliculasapp.application.config.ApiConfiguration;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;

@ExtendWith(MockitoExtension.class)
class MovieDetailsAdapterTest {
    @Mock
    private ApiConfiguration apiConfiguration;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private MovieDetailsAdapter movieDetailsAdapter;
    private Movie movie;

    @BeforeEach
    public void setUp() {
        movie = createMovie();
        reset(apiConfiguration, restTemplate);
    }

    @Test
    public void testGetMovieInfoByIdSuccess() {
        long movieId = 12345L;
        String apiUrl = "http://example.com/api/";
        String apiKey = "123456789";
        String endpoint = apiUrl + "movie/" + movieId + "?language=es-MX&api_key=" + apiKey;

        Mockito.when(apiConfiguration.getApiUrl()).thenReturn(apiUrl);
        Mockito.when(apiConfiguration.getApiKey()).thenReturn(apiKey);
        Mockito.when(restTemplate.getForEntity(endpoint, Movie.class)).thenReturn(new ResponseEntity<>(movie, HttpStatus.OK));

        Movie result = movieDetailsAdapter.getMovieInfoById(movieId);

        assertEquals(movie, result);
    }

    @Test
    public void testGetMovieInfoByIdNotFound() {
        long movieId = 12345L;
        String apiUrl = "http://example.com/api/";
        String apiKey = "123456789";
        String endpoint = apiUrl + "movie/" + movieId + "?language=es-MX&api_key=" + apiKey;

        Mockito.when(apiConfiguration.getApiUrl()).thenReturn(apiUrl);
        Mockito.when(apiConfiguration.getApiKey()).thenReturn(apiKey);
        Mockito.when(restTemplate.getForEntity(endpoint, Movie.class)).thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(MovieNotFoundException.class, () -> {
            movieDetailsAdapter.getMovieInfoById(movieId);
        });
    }

    private Movie createMovie() {
        long id = 1;
        String overview = "Descripcion de la pelicula";
        String status = "Estado de la pelcula";
        List<ProductionCompany> productionCompanies = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<ProductionCountries> productionCountries = new ArrayList<>();
        String title = "ttulo de la pelicula";
        float voteAverage = 4.5f;
        float voteCount = 1000;
        long revenue = 1000000;
        int budget = 500000;
        String posterPath = "/poster.jpg";
        float popularity = 7.8f;
        String releaseDate = "2024-03-18";

        return new Movie(id, overview, status, productionCompanies, genres, productionCountries, title, voteAverage, voteCount, revenue, budget, popularity, posterPath, releaseDate);
    }
}