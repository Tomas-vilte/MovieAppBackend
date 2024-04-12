package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.domain.models.*;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.TvSeriesEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.TvSeriesAlreadyExistsException;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.TvSeriesNotFoundException;
import com.peliculas.peliculasapp.infrastructure.adapter.mapper.TvSeriesEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TvSeriesRepositoryJpaImplTest {
    @Mock
    private TvSeriesRepository tvSeriesRepositoryJpa;
    @Mock
    private TvSeriesEntityMapper tvSeriesEntityMapper;
    @InjectMocks
    private TvSeriesRepositoryJpaImpl tvSeriesRepositoryJpaImpl;
    private TvSeries series;
    private TvSeriesEntity tvSeriesEntity;

    @BeforeEach
    public void setUp() {
        series = createTvSeries();
        tvSeriesEntity = createMockTvSeriesEntity(1L, "Game of Thrones");
    }

    @Test
    public void testSaveTvSeries() {
        // arrange
        TvSeriesEntity tvSeriesEntity = new TvSeriesEntity();

        Mockito.when(tvSeriesRepositoryJpa.existsByName(Mockito.anyString())).thenReturn(false);
        Mockito.when(tvSeriesEntityMapper.fromDomainModel(Mockito.any())).thenReturn(tvSeriesEntity);
        Mockito.when(tvSeriesRepositoryJpa.save(Mockito.any())).thenReturn(tvSeriesEntity);
        Mockito.when(tvSeriesEntityMapper.toDomainModel(Mockito.any())).thenReturn(series);

        Optional<TvSeries> result = tvSeriesRepositoryJpaImpl.saveTvSeries(series);

        // assert
        assertTrue(result.isPresent());
        assertEquals(series.getName(), result.get().getName());
    }

    @Test
    public void testSaveTvSeriesAlreadyExists() {
        Mockito.when(tvSeriesRepositoryJpa.existsByName(Mockito.anyString())).thenReturn(true);

        assertThrows(TvSeriesAlreadyExistsException.class, () -> {
            tvSeriesRepositoryJpaImpl.saveTvSeries(series);
        });
    }

    @Test
    public void testGetTvSeriesInfoExists() {
        Mockito.when(tvSeriesRepositoryJpa.findById(Mockito.anyLong())).thenReturn(Optional.of(tvSeriesEntity));
        Mockito.when(tvSeriesEntityMapper.toDomainModel(Mockito.any())).thenReturn(series);

        Optional<TvSeries> result = tvSeriesRepositoryJpaImpl.getTvSeriesById(series.getId());

        assertTrue(result.isPresent());
        assertEquals(series.getId(), result.get().getId());
    }

    @Test
    public void testGetTvSeriesInfoNotExists() {
        Mockito.when(tvSeriesRepositoryJpa.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        assertThrows(TvSeriesNotFoundException.class, () -> {
            tvSeriesRepositoryJpaImpl.getTvSeriesInfo(series.getId());
        });
    }

    public TvSeriesEntity createMockTvSeriesEntity(long id, String name) {
        TvSeriesEntity tvSeriesEntity = new TvSeriesEntity();
        tvSeriesEntity.setId(id);
        tvSeriesEntity.setName(name);
        return tvSeriesEntity;
    }

    public TvSeries createTvSeries() {
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