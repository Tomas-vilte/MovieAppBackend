package com.peliculas.peliculasapp.infrastructure.adapters;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.domain.ports.out.ExternalServicePort;
import com.peliculas.peliculasapp.infrastructure.config.ApiConfiguration;
import org.springframework.beans.factory.annotation.Value;
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
        String endpoint = apiConfiguration.getApiUrl() + "movie/" + movieId + "?language=es-MX";
        return new RestTemplate().getForObject(endpoint, Movie.class);
    }


    @Override
    public TvSeries getTvSeriesInfoById(long tvSeriesId) {
        String endpoint = apiConfiguration.getApiUrl() + "tv/" + tvSeriesId + "?language=es-MX";
        return new RestTemplate().getForObject(endpoint, TvSeries.class);
    }

}
