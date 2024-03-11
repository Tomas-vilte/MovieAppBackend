package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class TvSeriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String backdrop_path;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<CreatedSeriesEntity> created_by;
    private String first_air_date;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id", referencedColumnName = "id")
    private List<GenreEntity> genres;
    private String homepage;
    private boolean in_production;
    private String last_air_date;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "last_episode_id")
    private LastEpisodeEntity last_episode_to_air;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_episode_id")
    private NextEpisodeEntity next_episode_to_air;

    @ManyToOne
    @JoinColumn(name = "networks_id")
    private NetworksEntity networks;
    private int number_of_episodes;
    private int number_of_seasons;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<OriginCountryEntity> origin_country;
    private String original_language;
    private String original_name;
    private String overview;
    private float popularity;
    private String poster_path;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<ProductionCompaniesEntity> production_companies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<ProductionCountriesEntity> production_countries;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<SeasonsEntity> seasons;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<SpokenLanguagesEntity> spoken_languages;
    private String status;
    private String tagline;
    private float vote_average;
    private int vote_count;
}
