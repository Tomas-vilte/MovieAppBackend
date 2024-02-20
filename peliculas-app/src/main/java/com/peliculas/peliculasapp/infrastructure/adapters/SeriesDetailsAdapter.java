package com.peliculas.peliculasapp.infrastructure.adapters;
import com.peliculas.peliculasapp.application.config.ApiConfiguration;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesServicePort;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class SeriesDetailsAdapter implements TvSeriesServicePort {
    private final ApiConfiguration apiConfiguration;

    private final RestTemplate restTemplate;

    public SeriesDetailsAdapter(ApiConfiguration apiConfiguration, RestTemplate restTemplate) {
        this.apiConfiguration = apiConfiguration;
        this.restTemplate = restTemplate;
    }

    @Override
    public TvSeries getTvSeriesInfoById(long tvSeriesId) {
        String endpoint = apiConfiguration.getApiUrl() + "tv/" + tvSeriesId + "?language=es-MX";
        ResponseEntity<TvSeries> response = restTemplate.getForEntity(endpoint, TvSeries.class);
        return response.getBody();
    }
}
