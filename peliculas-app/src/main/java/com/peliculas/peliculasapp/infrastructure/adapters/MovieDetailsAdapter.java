package com.peliculas.peliculasapp.infrastructure.adapters;
import com.peliculas.peliculasapp.application.config.ApiConfiguration;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.application.ports.out.ExternalServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieDetailsAdapter implements ExternalServicePort {

    private final ApiConfiguration apiConfiguration;

    public MovieDetailsAdapter(ApiConfiguration apiConfiguration) {
        this.apiConfiguration = apiConfiguration;
    }


    @Override
    public Movie getMovieInfoById(long movieId) {
        String endpoint = apiConfiguration.getApiUrl() + "movie/" + movieId + "?language=es-MX&api_key=" + apiConfiguration.getApiKey();
        ResponseEntity<Movie> response = new RestTemplate().getForEntity(endpoint, Movie.class);
        return response.getBody();
    }


    @Override
    public TvSeries getTvSeriesInfoById(long tvSeriesId) {
        String endpoint = apiConfiguration.getApiUrl() + "tv/" + tvSeriesId + "?language=es-MX";
        return new RestTemplate().getForObject(endpoint, TvSeries.class);
    }

}
