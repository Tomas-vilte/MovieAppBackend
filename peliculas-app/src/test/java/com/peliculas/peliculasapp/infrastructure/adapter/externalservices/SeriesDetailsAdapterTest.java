package com.peliculas.peliculasapp.infrastructure.adapter.externalservices;
import com.peliculas.peliculasapp.application.config.ApiConfiguration;
import com.peliculas.peliculasapp.domain.models.*;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.TvSeriesNotFoundException;
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
class SeriesDetailsAdapterTest {
    @Mock
    private ApiConfiguration apiConfiguration;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private SeriesDetailsAdapter seriesDetailsAdapter;
    private TvSeries series;

    @BeforeEach
    public void setUp() {
        series = createSeries();
        reset(apiConfiguration, restTemplate);
    }

    @Test
    public void getTvSeriesInfoById() {
        long tvSeriesId = 12345L;
        String apiUrl = "http://example.com/api/";
        String apiKey = "123456789";
        String endpoint = apiUrl + "tv/" + tvSeriesId + "?language=es-MX&api_key=" + apiKey;

        Mockito.when(apiConfiguration.getApiUrl()).thenReturn(apiUrl);
        Mockito.when(apiConfiguration.getApiKey()).thenReturn(apiKey);
        Mockito.when(restTemplate.getForEntity(endpoint, TvSeries.class)).thenReturn(new ResponseEntity<>(series, HttpStatus.OK));

        TvSeries result = seriesDetailsAdapter.getTvSeriesInfoById(tvSeriesId);

        assertEquals(series, result);
    }

    @Test
    public void testGetTvSeriesInfoByIdNotFound() {
        long tvSeriesId = 12345L;
        String apiUrl = "http://example.com/api/";
        String apiKey = "123456789";
        String endpoint = apiUrl + "tv/" + tvSeriesId + "?language=es-MX&api_key=" + apiKey;

        Mockito.when(apiConfiguration.getApiUrl()).thenReturn(apiUrl);
        Mockito.when(apiConfiguration.getApiKey()).thenReturn(apiKey);
        Mockito.when(restTemplate.getForEntity(endpoint, TvSeries.class)).thenThrow(HttpClientErrorException.NotFound.class);

        assertThrows(TvSeriesNotFoundException.class, () -> seriesDetailsAdapter.getTvSeriesInfoById(tvSeriesId));
    }

    public TvSeries createSeries() {
        TvSeries tvSeries = new TvSeries();

        tvSeries.setId(12);
        tvSeries.setName("Breaking Bad");
        tvSeries.setBackdrop_path("/zzWGRw277MNoCs3zhyG3YmYQsXv.jpg");
        tvSeries.setFirst_air_date("2008-01-20");
        tvSeries.setHomepage("https://www.amc.com/shows/breaking-bad");
        tvSeries.setIn_production(false);
        tvSeries.setLast_air_date("2013-09-29");
        tvSeries.setName("Breaking Bad");
        tvSeries.setNumber_of_episodes(62);
        tvSeries.setNumber_of_seasons(5);
        tvSeries.setOriginal_language("en");
        tvSeries.setOriginal_name("Breaking Bad");
        tvSeries.setOverview("Breaking Bad follows protagonist Walter White, a chemistry teacher who lives in New Mexico with his wife and teenage son who has cerebral palsy.");
        tvSeries.setPopularity(135.328f);
        tvSeries.setPoster_path("/zzWGRw277MNoCs3zhyG3YmYQsXv.jpg");
        tvSeries.setStatus("Ended");
        tvSeries.setTagline("All Hail the King");
        tvSeries.setVote_average(9.3f);
        tvSeries.setVote_count(14102);

        List<CreatedSeries> createdSeriesList = new ArrayList<>();
        CreatedSeries creator = new CreatedSeries();
        creator.setName("Vince Gilligan");
        createdSeriesList.add(creator);
        tvSeries.setCreated_by(createdSeriesList);

        List<Genre> genreList = new ArrayList<>();
        Genre genre = new Genre();
        genre.setName("Drama");
        genreList.add(genre);
        tvSeries.setGenres(genreList);

        LastEpisode lastEpisode = new LastEpisode();
        lastEpisode.setEpisode_number(16);
        lastEpisode.setName("Felina");
        lastEpisode.setAir_date("2013-09-29");
        tvSeries.setLast_episode_to_air(lastEpisode);

        List<Networks> networksList = new ArrayList<>();
        Networks network = new Networks();
        network.setName("AMC");
        networksList.add(network);
        tvSeries.setNetworks(networksList);

        List<String> originCountryList = new ArrayList<>();
        originCountryList.add("US");
        tvSeries.setOrigin_country(originCountryList);

        List<Seasons> seasonsList = new ArrayList<>();
        Seasons season = new Seasons();
        season.setSeason_number(1);
        seasonsList.add(season);
        tvSeries.setSeasons(seasonsList);

        List<ProductionCompany> productionCompanyList = new ArrayList<>();
        ProductionCompany productionCompany = new ProductionCompany();
        productionCompany.setName("Sony Pictures Television");
        productionCompanyList.add(productionCompany);
        tvSeries.setProduction_companies(productionCompanyList);

        List<ProductionCountries> productionCountriesList = new ArrayList<>();
        ProductionCountries productionCountry = new ProductionCountries();
        productionCountry.setName("United States of America");
        productionCountriesList.add(productionCountry);
        tvSeries.setProduction_countries(productionCountriesList);

        List<SpokenLanguages> spokenLanguagesList = new ArrayList<>();
        SpokenLanguages spokenLanguage = new SpokenLanguages();
        spokenLanguage.setName("English");
        spokenLanguagesList.add(spokenLanguage);
        tvSeries.setSpoken_languages(spokenLanguagesList);
        return tvSeries;
    }
}