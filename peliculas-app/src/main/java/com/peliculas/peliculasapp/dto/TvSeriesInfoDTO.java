package com.peliculas.peliculasapp.dto;
import com.peliculas.peliculasapp.domain.models.*;
import lombok.Data;

import java.util.List;

@Data
public class TvSeriesInfoDTO {
    private String name;
    private String poster_path;
    private String original_name;
    private String backdrop_path;
    private String overview;
    private boolean in_production;
    private String homepage;
    private String original_language;
    private float popularity;
    private String status;
    private String tagline;
    private float vote_average;
    private int vote_count;
    private List<CreatedSeries> created_by;
    private List<Genre> genres;
    private String last_air_date;
    private int number_of_episodes;
    private int number_of_seasons;
    private LastEpisode last_episode_to_air;
    private NextEpisode next_episode_to_air;
    private List<Networks> networks;
    private List<String> origin_country;
    private List<Seasons> seasons;
    private List<ProductionCompany> production_companies;
    private List<ProductionCountries> production_countries;
    private List<SpokenLanguages> spoken_languages;
}
