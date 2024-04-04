package com.peliculas.peliculasapp.infrastructure.adapter.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
public class TvSeriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String backdropPath;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<CreatedSeriesEntity> createdBy;
    private String firstAirDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id", referencedColumnName = "id")
    private List<GenreEntity> genres;
    private String homePage;
    private boolean inProduction;
    private String lastAirDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "last_episode_id")
    private LastEpisodeEntity lastEpisodeToAir;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_episode_id")
    private NextEpisodeEntity nextEpisodeToAir;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tv_series_networks",
            joinColumns = @JoinColumn(name = "tv_series_id"),
            inverseJoinColumns = @JoinColumn(name = "networks_id")
    )
    private List<NetworksEntity> networks;
    private int numberOfEpisodes;
    private int numberOfSeasons;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<OriginCountryEntity> originCountry;
    private String originalLanguage;
    private String originalName;
    @Column(length = 1000)
    private String overview;
    private float popularity;
    private String posterPath;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<ProductionCompaniesEntity> productionCompanies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<ProductionCountriesEntity> productionCountries;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<SeasonsEntity> seasons;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_series_id")
    private List<SpokenLanguagesEntity> spokenLanguages;
    private String status;
    private String tagline;
    private float voteAverage;
    private int voteCount;

    public TvSeriesEntity() {}

    public TvSeriesEntity(long id, String backdropPath, List<CreatedSeriesEntity> createdBy, String firstAirDate, List<GenreEntity> genres, String homePage, boolean inProduction, String lastAirDate, LastEpisodeEntity lastEpisodeToAir, String name, NextEpisodeEntity nextEpisodeToAir, List<NetworksEntity> networks, int numberOfEpisodes, int numberOfSeasons, List<OriginCountryEntity> originCountry, String originalLanguage, String originalName, String overview, float popularity, String posterPath, List<ProductionCompaniesEntity> productionCompanies, List<ProductionCountriesEntity> productionCountries, List<SeasonsEntity> seasons, List<SpokenLanguagesEntity> spokenLanguages, String status, String tagline, float voteAverage, int voteCount) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.createdBy = createdBy;
        this.firstAirDate = firstAirDate;
        this.genres = genres;
        this.homePage = homePage;
        this.inProduction = inProduction;
        this.lastAirDate = lastAirDate;
        this.lastEpisodeToAir = lastEpisodeToAir;
        this.name = name;
        this.nextEpisodeToAir = nextEpisodeToAir;
        this.networks = networks;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.originCountry = originCountry;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.seasons = seasons;
        this.spokenLanguages = spokenLanguages;
        this.status = status;
        this.tagline = tagline;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }
}
