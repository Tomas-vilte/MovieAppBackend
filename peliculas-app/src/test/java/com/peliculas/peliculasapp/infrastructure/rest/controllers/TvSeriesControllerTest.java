package com.peliculas.peliculasapp.infrastructure.rest.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peliculas.peliculasapp.application.services.TvSeriesService;
import com.peliculas.peliculasapp.domain.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.domain.dto.TvSeriesInfoDTO;
import com.peliculas.peliculasapp.domain.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(TvSeriesController.class)
class TvSeriesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TvSeriesService tvSeriesService;
    @Autowired
    private ObjectMapper objectMapper;
    private long id;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TvSeriesController(tvSeriesService)).build();
        objectMapper = new ObjectMapper();
        id = 1;
    }

    @Test
    public void getAndSaveTvSeriesInfo_Success() throws Exception {
        // Arrange
        TvSeriesDTO seriesDTO = createDummyTvSeries();
        Mockito.when(tvSeriesService.saveTvSeriesInfo(id)).thenReturn(seriesDTO);

        // act y assert
        mockMvc.perform(post("/api/series/{tvSeriesId}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Serie guardada con exito en la base de datos."))
                .andExpect(jsonPath("$.data.name").value(seriesDTO.getName()))
                .andExpect(jsonPath("$.data.overview").value(seriesDTO.getOverview()))
                .andExpect(jsonPath("$.data.number_of_episodes").value(seriesDTO.getNumber_of_episodes()))
                .andExpect(jsonPath("$.data.number_of_seasons").value(seriesDTO.getNumber_of_seasons()))
                .andExpect(jsonPath("$.data.popularity").value(seriesDTO.getPopularity()));
    }

    @Test
    public void getTvSeriesById_Success() throws Exception {
        // arrange
        TvSeriesInfoDTO seriesDTO = createSampleTvSeriesInfoDTO();
        Mockito.when(tvSeriesService.getTvSeriesInfoById(id)).thenReturn(seriesDTO);

        // act y assert
        mockMvc.perform(get("/api/series/{tvSeriesId}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Serie obtenida con exito"))
                .andExpect(jsonPath("$.data.name").value("Breaking Bad"))
                .andExpect(jsonPath("$.data.overview").value(seriesDTO.getOverview()))
                .andExpect(jsonPath("$.data.number_of_episodes").value(62))
                .andExpect(jsonPath("$.data.number_of_seasons").value(5))
                .andExpect(jsonPath("$.data.popularity").value(135.328f));

    }

    public static TvSeriesDTO createDummyTvSeries() {
        TvSeriesDTO tvSeries = new TvSeriesDTO();
        tvSeries.setName("Game of Thrones");
        tvSeries.setOverview("A fantasy drama television series based on George R. R. Martin's A Song of Ice and Fire novels.");
        tvSeries.setNumber_of_episodes(73);
        tvSeries.setNumber_of_seasons(8);
        tvSeries.setPopularity(9);
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