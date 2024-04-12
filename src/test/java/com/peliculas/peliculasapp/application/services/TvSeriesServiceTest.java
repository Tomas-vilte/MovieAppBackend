package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveTvSeriesInfoUseCase;
import com.peliculas.peliculasapp.domain.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.domain.dto.TvSeriesInfoDTO;
import com.peliculas.peliculasapp.domain.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TvSeriesServiceTest {
    private GetAndSaveTvSeriesInfoUseCase getAndSaveTvSeriesInfoUseCase;
    private TvSeriesService tvSeriesService;

    @BeforeEach
    public void setUp() {
        getAndSaveTvSeriesInfoUseCase = Mockito.mock(GetAndSaveTvSeriesInfoUseCase.class);
        tvSeriesService = new TvSeriesService(getAndSaveTvSeriesInfoUseCase);
    }

    @Test
    public void testSaveTvSeriesInfo() {
        // arrange
        long tvSeriesId = 1;
        TvSeriesDTO expectedDto = createSampleTvSeriesDto();
        Mockito.when(getAndSaveTvSeriesInfoUseCase.getAndSaveTvSeriesInfo(tvSeriesId)).thenReturn(expectedDto);

        // act
        TvSeriesDTO result = tvSeriesService.saveTvSeriesInfo(tvSeriesId);

        // assert
        assertNotNull(result);
        assertEquals(expectedDto.getName(), result.getName());
        assertEquals(expectedDto.getOverview(), result.getOverview());
        assertEquals(expectedDto.getNumber_of_episodes(), result.getNumber_of_episodes());
        assertEquals(expectedDto.getNumber_of_seasons(), result.getNumber_of_seasons());
        assertEquals(expectedDto.getPopularity(), result.getPopularity());
    }

    @Test
    public void testGetTvSeriesInfoById() {
        // arrange
        long tvSeriesId = 1;
        TvSeriesInfoDTO expectedDto = createSampleTvSeriesInfoDTO();
        Mockito.when(getAndSaveTvSeriesInfoUseCase.getTvSeriesInfoById(tvSeriesId)).thenReturn(expectedDto);

        // act
        TvSeriesInfoDTO result = tvSeriesService.getTvSeriesInfoById(tvSeriesId);

        // assert
        assertNotNull(result);
        assertEquals(expectedDto.getName(), result.getName());
        assertEquals(expectedDto.getHomepage(), result.getHomepage());
        assertEquals(expectedDto.getOverview(), result.getOverview());
        assertEquals(expectedDto.getPopularity(), result.getPopularity());
        assertEquals(expectedDto.getStatus(), result.getStatus());
        assertEquals(expectedDto.getProduction_companies(), result.getProduction_companies());
        assertEquals(expectedDto.getProduction_countries(), result.getProduction_countries());
        assertEquals(expectedDto.getGenres(), result.getGenres());
        assertEquals(expectedDto.getVote_average(), result.getVote_average());
        assertEquals(expectedDto.getVote_count(), result.getVote_count());
        assertEquals(expectedDto.getPoster_path(), result.getPoster_path());
    }

    private TvSeriesDTO createSampleTvSeriesDto() {
        TvSeriesDTO tvSeriesDTO = new TvSeriesDTO();
        tvSeriesDTO.setName("Dummy TV Series");
        tvSeriesDTO.setOverview("This is a dummy TV series overview.");
        tvSeriesDTO.setNumber_of_episodes(10);
        tvSeriesDTO.setNumber_of_seasons(2);
        tvSeriesDTO.setPopularity(8.5f);
        return tvSeriesDTO;
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

        List<CreatedSeries> createdSeriesList = new ArrayList<>();
        CreatedSeries creator = new CreatedSeries();
        creator.setName("Vince Gilligan");
        createdSeriesList.add(creator);
        tvSeriesInfoDTO.setCreated_by(createdSeriesList);

        List<Genre> genreList = new ArrayList<>();
        Genre genre = new Genre();
        genre.setName("Drama");
        genreList.add(genre);
        tvSeriesInfoDTO.setGenres(genreList);

        LastEpisode lastEpisode = new LastEpisode();
        lastEpisode.setEpisode_number(16);
        lastEpisode.setName("Felina");
        lastEpisode.setAir_date("2013-09-29");
        tvSeriesInfoDTO.setLast_episode_to_air(lastEpisode);

        List<Networks> networksList = new ArrayList<>();
        Networks network = new Networks();
        network.setName("AMC");
        networksList.add(network);
        tvSeriesInfoDTO.setNetworks(networksList);

        List<String> originCountryList = new ArrayList<>();
        originCountryList.add("US");
        tvSeriesInfoDTO.setOrigin_country(originCountryList);

        List<Seasons> seasonsList = new ArrayList<>();
        Seasons season = new Seasons();
        season.setSeason_number(1);
        seasonsList.add(season);
        tvSeriesInfoDTO.setSeasons(seasonsList);

        List<ProductionCompany> productionCompanyList = new ArrayList<>();
        ProductionCompany productionCompany = new ProductionCompany();
        productionCompany.setName("Sony Pictures Television");
        productionCompanyList.add(productionCompany);
        tvSeriesInfoDTO.setProduction_companies(productionCompanyList);

        List<ProductionCountries> productionCountriesList = new ArrayList<>();
        ProductionCountries productionCountry = new ProductionCountries();
        productionCountry.setName("United States of America");
        productionCountriesList.add(productionCountry);
        tvSeriesInfoDTO.setProduction_countries(productionCountriesList);

        List<SpokenLanguages> spokenLanguagesList = new ArrayList<>();
        SpokenLanguages spokenLanguage = new SpokenLanguages();
        spokenLanguage.setName("English");
        spokenLanguagesList.add(spokenLanguage);
        tvSeriesInfoDTO.setSpoken_languages(spokenLanguagesList);

        return tvSeriesInfoDTO;
    }
}