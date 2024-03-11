package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class TvSeriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String backdrop_path;
    private List<CreatedSeriesEntity> created_by;
    private String first_air_date;
    private List<GenreEntity> genres;
    private String homepage;
    private boolean in_production;
    private String last_air_date;
    private LastEpisodeEntity last_episode_to_air;
    private String name;
    private NextEpisodeEntity next_episode_to_air;
    private NetworksEntity networks;
    private int number_of_episodes;
    private int number_of_seasons;
    private List<OriginCountryEntity> origin_country;
    private String original_language;
    private String original_name;
    private String overview;
    private float popularity;
    private String poster_path;
    private List<ProductionCompaniesEntity> production_companies;
    private List<ProductionCountriesEntity> production_countries;
    private List<SeasonsEntity> seasons;
    private List<SpokenLanguagesEntity> spoken_languages;
    private String status;
    private String tagline;
    private float vote_average;
    private int vote_count;
}
