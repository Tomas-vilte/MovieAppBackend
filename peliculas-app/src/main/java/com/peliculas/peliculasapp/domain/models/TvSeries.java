package com.peliculas.peliculasapp.domain.models;
import java.util.Date;
import java.util.List;

public class TvSeries {

    private long id;
    private String backdrop_path;
    private List<CreatedSeries> created_by;
    private String first_air_date;
    private List<Genre> genres;
    private String homepage;
    private boolean in_production;
    private String last_air_date;
    private LastEpisode last_episode_to_air;
    private String name;
    private NextEpisode next_episode_to_air;
    private Networks networks;
    private int number_of_episodes;
    private int number_of_seasons;
    private List<OriginCountry> origin_country;
    private String original_language;
    private String original_name;
    private String overview;
    private float popularity;
    private String poster_path;
    private List<ProductionCompany> production_companies;
    private List<ProductionCountries> production_countries;
    private List<Seasons> seasons;
    private List<SpokenLanguages> spoken_languages;
    private String status;
    private String tagline;
    private float vote_average;
    private int vote_count;

}
