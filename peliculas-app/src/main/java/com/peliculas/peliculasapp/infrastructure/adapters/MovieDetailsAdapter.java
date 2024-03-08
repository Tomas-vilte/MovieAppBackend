package com.peliculas.peliculasapp.infrastructure.adapters;
import com.peliculas.peliculasapp.application.config.ApiConfiguration;
import com.peliculas.peliculasapp.application.ports.out.MovieServicePort;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.infrastructure.exceptions.MovieNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieDetailsAdapter implements MovieServicePort {
    private final ApiConfiguration apiConfiguration;
    private final RestTemplate restTemplate;

    public MovieDetailsAdapter(ApiConfiguration apiConfiguration, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.apiConfiguration = apiConfiguration;
    }


    @Override
    public Movie getMovieInfoById(long movieId) {
        try {
            String endpoint = apiConfiguration.getApiUrl() + "movie/" + movieId + "?language=es-MX&api_key=" + apiConfiguration.getApiKey();
            ResponseEntity<Movie> response = restTemplate.getForEntity(endpoint, Movie.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new MovieNotFoundException("Pelicula con ID: " + movieId + " no encontrada");
        }
    }
}
