package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveMovieInfoUseCase;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveTvSeriesInfoUseCase;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesRepositoryPort;
import com.peliculas.peliculasapp.domain.models.*;
import com.peliculas.peliculasapp.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.dto.TvSeriesInfoDTO;
import com.peliculas.peliculasapp.infrastructure.adapters.MovieDetailsAdapter;
import com.peliculas.peliculasapp.infrastructure.adapters.SeriesDetailsAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAndSaveTvSeriesInfoUseCaseImplTest {

    private TvSeriesRepositoryPort tvSeriesRepositoryPort;
    private SeriesDetailsAdapter seriesDetailsAdapter;
    private ModelMapper modelMapper;
    private ValueOperations<String, Object> valueOperations;
    private RedisTemplate<String, Object> redisTemplate;
    private GetAndSaveTvSeriesInfoUseCase service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tvSeriesRepositoryPort = Mockito.mock(TvSeriesRepositoryPort.class);
        seriesDetailsAdapter = Mockito.mock(SeriesDetailsAdapter.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        redisTemplate = Mockito.mock(RedisTemplate.class);
        valueOperations = Mockito.mock(ValueOperations.class);

        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        service = new GetAndSaveTvSeriesInfoUseCaseImpl(
                tvSeriesRepositoryPort,
                seriesDetailsAdapter,
                modelMapper,
                valueOperations
        );
    }

    @Test
    void testGetTvSeriesByIdFromCache() {
        // arange
        long tvSeriesId = 5;
        TvSeriesInfoDTO expectedTvSeriesInfoDTO = createSampleTvSeriesInfoDTO();

        Mockito.when(valueOperations.get("series:" + tvSeriesId)).thenReturn(expectedTvSeriesInfoDTO);

        // act
        TvSeriesInfoDTO result = service.getTvSeriesInfoById(tvSeriesId);

        // assert
        assertEquals(expectedTvSeriesInfoDTO, result);
        verify(valueOperations, times(1)).get("series:" + tvSeriesId);
        verify(tvSeriesRepositoryPort, never()).getTvSeriesById(tvSeriesId);
    }

    @Test
    void testGetAndSaveTvSeriesInfo() {

        // arrange
        long tvSeriesId = 2;
        TvSeriesDTO expectedTvSeriesDTO = new TvSeriesDTO();
        expectedTvSeriesDTO.setName("Breaking bad");
        expectedTvSeriesDTO.setOverview("El pelado mas capo");
        expectedTvSeriesDTO.setNumber_of_episodes(5);
        expectedTvSeriesDTO.setNumber_of_seasons(7);
        expectedTvSeriesDTO.setPopularity(2323245);

        TvSeries series = ofTesting();
        Mockito.when(seriesDetailsAdapter.getTvSeriesInfoById(tvSeriesId)).thenReturn(series);
        Mockito.when(modelMapper.map(series, TvSeriesDTO.class)).thenReturn(expectedTvSeriesDTO);

        // act
        TvSeriesDTO result = service.getAndSaveTvSeriesInfo(tvSeriesId);

        // assert
        assertEquals(expectedTvSeriesDTO, result);
        verify(tvSeriesRepositoryPort, times(1)).saveTvSeries(series);

    }

    @Test
    void testGetTvSeriesByIdFromDatabase() {

        // arrange
        long tvSeriesId = 6;
        TvSeriesInfoDTO expectedTvSeriesInfoDTO = createSampleTvSeriesInfoDTO();

        Optional<TvSeries> series = Optional.of(ofTesting());
        Mockito.when(tvSeriesRepositoryPort.getTvSeriesById(tvSeriesId)).thenReturn(series);
        Mockito.when(modelMapper.map(series, TvSeriesInfoDTO.class)).thenReturn(expectedTvSeriesInfoDTO);

        // act
        TvSeriesInfoDTO result = service.getTvSeriesInfoById(tvSeriesId);

        // assert
        assertNotNull(result);
        assertEquals(expectedTvSeriesInfoDTO, result);
        verify(redisTemplate, atMost(1)).opsForValue();
        verify(tvSeriesRepositoryPort, times(1)).getTvSeriesById(tvSeriesId);
        verify(valueOperations, times(1)).set(eq("series:" + tvSeriesId), eq(expectedTvSeriesInfoDTO), eq(Duration.ofMinutes(1)));
    }

    @Test
    void testGetTvSeriesByIdFromDatabaseWhenMovieDoesNotExist() {
        // arrange
        long tvSeriesId = 6;

        Optional<TvSeries> series = Optional.empty();
        when(tvSeriesRepositoryPort.getTvSeriesById(tvSeriesId)).thenReturn(series);

        // act
        TvSeriesInfoDTO result = service.getTvSeriesInfoById(tvSeriesId);

        // assert
        assertNull(result);
        verify(redisTemplate, never()).opsForValue();
        verify(tvSeriesRepositoryPort, times(1)).getTvSeriesById(tvSeriesId);
    }

    private TvSeries ofTesting() {
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

    private TvSeriesInfoDTO createSampleTvSeriesInfoDTO() {
        TvSeriesInfoDTO tvSeriesInfoDTO = new TvSeriesInfoDTO();

        tvSeriesInfoDTO.setName("Breaking Bad");
        tvSeriesInfoDTO.setPoster_path("/zzWGRw277MNoCs3zhyG3YmYQsXv.jpg");
        tvSeriesInfoDTO.setOriginal_name("Breaking Bad");
        tvSeriesInfoDTO.setBackdrop_path("/tsRy63Mu5cu8etL1X7ZLyf7UP1M.jpg");
        tvSeriesInfoDTO.setOverview("Breaking Bad follows protagonist Walter White, a chemistry teacher who lives in New Mexico with his wife and teenage son who has cerebral palsy.");
        tvSeriesInfoDTO.setIn_production(false);
        tvSeriesInfoDTO.setHomepage("https://www.amc.com/shows/breaking-bad");
        tvSeriesInfoDTO.setOriginal_language("en");
        tvSeriesInfoDTO.setPopularity(135.328f);
        tvSeriesInfoDTO.setStatus("Ended");
        tvSeriesInfoDTO.setTagline("All Hail the King");
        tvSeriesInfoDTO.setVote_average(9.3f);
        tvSeriesInfoDTO.setVote_count(14102);
        tvSeriesInfoDTO.setLast_air_date("2013-09-29");
        tvSeriesInfoDTO.setNumber_of_episodes(62);
        tvSeriesInfoDTO.setNumber_of_seasons(5);

        // Set sample data for created_by list
        List<CreatedSeries> createdSeriesList = new ArrayList<>();
        CreatedSeries creator = new CreatedSeries();
        creator.setName("Vince Gilligan");
        createdSeriesList.add(creator);
        tvSeriesInfoDTO.setCreated_by(createdSeriesList);

        // Set sample data for genres list
        List<Genre> genreList = new ArrayList<>();
        Genre genre = new Genre();
        genre.setName("Drama");
        genreList.add(genre);
        tvSeriesInfoDTO.setGenres(genreList);

        // Set sample data for last_episode_to_air
        LastEpisode lastEpisode = new LastEpisode();
        lastEpisode.setEpisode_number(16);
        lastEpisode.setName("Felina");
        lastEpisode.setAir_date("2013-09-29");
        tvSeriesInfoDTO.setLast_episode_to_air(lastEpisode);

        // Set sample data for networks list
        List<Networks> networksList = new ArrayList<>();
        Networks network = new Networks();
        network.setName("AMC");
        networksList.add(network);
        tvSeriesInfoDTO.setNetworks(networksList);

        // Set sample data for origin_country list
        List<String> originCountryList = new ArrayList<>();
        originCountryList.add("US");
        tvSeriesInfoDTO.setOrigin_country(originCountryList);

        // Set sample data for seasons list
        List<Seasons> seasonsList = new ArrayList<>();
        Seasons season = new Seasons();
        season.setSeason_number(1);
        seasonsList.add(season);
        tvSeriesInfoDTO.setSeasons(seasonsList);

        // Set sample data for production_companies list
        List<ProductionCompany> productionCompanyList = new ArrayList<>();
        ProductionCompany productionCompany = new ProductionCompany();
        productionCompany.setName("Sony Pictures Television");
        productionCompanyList.add(productionCompany);
        tvSeriesInfoDTO.setProduction_companies(productionCompanyList);

        // Set sample data for production_countries list
        List<ProductionCountries> productionCountriesList = new ArrayList<>();
        ProductionCountries productionCountry = new ProductionCountries();
        productionCountry.setName("United States of America");
        productionCountriesList.add(productionCountry);
        tvSeriesInfoDTO.setProduction_countries(productionCountriesList);

        // Set sample data for spoken_languages list
        List<SpokenLanguages> spokenLanguagesList = new ArrayList<>();
        SpokenLanguages spokenLanguage = new SpokenLanguages();
        spokenLanguage.setName("English");
        spokenLanguagesList.add(spokenLanguage);
        tvSeriesInfoDTO.setSpoken_languages(spokenLanguagesList);

        return tvSeriesInfoDTO;
    }
}