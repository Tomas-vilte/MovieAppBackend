package com.peliculas.peliculasapp.domain.models;
import lombok.Data;
import java.util.List;

@Data
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
    private List<Networks> networks;
    private int number_of_episodes;
    private int number_of_seasons;
    private List<String> origin_country;
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


    public TvSeries(long id, String backdrop_path, List<CreatedSeries> created_by, String first_air_date, List<Genre> genres, String homepage, boolean in_production, String last_air_date, LastEpisode last_episode_to_air, String name, NextEpisode next_episode_to_air, List<Networks> networks, int number_of_episodes, int number_of_seasons, List<String> origin_country, String original_language, String original_name, String overview, float popularity, String poster_path, List<ProductionCompany> production_companies, List<ProductionCountries> production_countries, List<Seasons> seasons, List<SpokenLanguages> spoken_languages, String status, String tagline, float vote_average, int vote_count) {
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.created_by = created_by;
        this.first_air_date = first_air_date;
        this.genres = genres;
        this.homepage = homepage;
        this.in_production = in_production;
        this.last_air_date = last_air_date;
        this.last_episode_to_air = last_episode_to_air;
        this.name = name;
        this.next_episode_to_air = next_episode_to_air;
        this.networks = networks;
        this.number_of_episodes = number_of_episodes;
        this.number_of_seasons = number_of_seasons;
        this.origin_country = origin_country;
        this.original_language = original_language;
        this.original_name = original_name;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.production_companies = production_companies;
        this.production_countries = production_countries;
        this.seasons = seasons;
        this.spoken_languages = spoken_languages;
        this.status = status;
        this.tagline = tagline;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }
}
