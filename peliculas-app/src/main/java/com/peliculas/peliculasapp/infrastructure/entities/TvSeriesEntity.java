package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.*;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
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

    public static TvSeriesEntity fromDomainModel(TvSeries tvSeries) {
        List<CreatedSeriesEntity> createdSeriesEntityList = tvSeries.getCreated_by().stream()
                .map(CreatedSeriesEntity::fromDomainModel)
                .toList();

        List<GenreEntity> genreEntityList = tvSeries.getGenres().stream()
                .map(GenreEntity::fromDomainModel)
                .toList();

        LastEpisodeEntity lastEpisodeEntity = tvSeries.getLast_episode_to_air() != null ?
                LastEpisodeEntity.fromDomainModel(tvSeries.getLast_episode_to_air()) :
                null;

        NextEpisodeEntity nextEpisodeEntity = tvSeries.getNext_episode_to_air() != null ?
                NextEpisodeEntity.fromDomainModel(tvSeries.getNext_episode_to_air()) :
                null;

        List<NetworksEntity> networksEntity = tvSeries.getNetworks().stream()
                .map(NetworksEntity::fromDomainModel)
                .toList();

        List<OriginCountryEntity> originCountryEntities = tvSeries.getOrigin_country().stream()
                .map(OriginCountryEntity::fromDomainModel)
                .toList();

        List<ProductionCountriesEntity> productionCountriesEntities = tvSeries.getProduction_countries().stream()
                .map(ProductionCountriesEntity::fromDomainModel)
                .toList();

        List<ProductionCompaniesEntity> productionCompanyEntities = tvSeries.getProduction_companies().stream()
                .map(ProductionCompaniesEntity::fromDomainModel)
                .toList();

        List<SeasonsEntity> seasonsEntities = tvSeries.getSeasons().stream()
                .map(SeasonsEntity::fromDomainModel)
                .toList();

        List<SpokenLanguagesEntity> spokenLanguagesEntities = tvSeries.getSpoken_languages().stream()
                .map(SpokenLanguagesEntity::fromDomainModel)
                .toList();

        return new TvSeriesEntity(
                tvSeries.getId(),
                tvSeries.getBackdrop_path(),
                createdSeriesEntityList,
                tvSeries.getFirst_air_date(),
                genreEntityList,
                tvSeries.getHomepage(),
                tvSeries.isIn_production(),
                tvSeries.getLast_air_date(),
                lastEpisodeEntity,
                tvSeries.getName(),
                nextEpisodeEntity,
                networksEntity,
                tvSeries.getNumber_of_episodes(),
                tvSeries.getNumber_of_seasons(),
                originCountryEntities,
                tvSeries.getOriginal_language(),
                tvSeries.getOriginal_name(),
                tvSeries.getOverview(),
                tvSeries.getPopularity(),
                tvSeries.getPoster_path(),
                productionCompanyEntities,
                productionCountriesEntities,
                seasonsEntities,
                spokenLanguagesEntities,
                tvSeries.getStatus(),
                tvSeries.getTagline(),
                tvSeries.getVote_average(),
                tvSeries.getVote_count()

        );
    }

    public Optional<TvSeries> toDomainModel() {
        List<CreatedSeries> createdSeries = this.createdBy.stream()
                .map(CreatedSeriesEntity::toDomainModel)
                .toList();

        List<Genre> genreList = this.genres.stream()
                .map(GenreEntity::toDomainModel)
                .toList();

        LastEpisode lastEpisodeList = this.lastEpisodeToAir.toDomainModel();

        NextEpisode nextEpisodeList = this.nextEpisodeToAir.toDomainModel();

        List<Networks> networksList = this.networks.stream()
                .map(NetworksEntity::toDomainModel)
                .toList();

        List<String> originCountryList = this.originCountry.stream()
                .map(OriginCountryEntity::toDomainModel)
                .toList();

        List<ProductionCountries> productionCountries = this.productionCountries.stream()
                .map(ProductionCountriesEntity::toDomainModel)
                .collect(Collectors.toList());

        List<ProductionCompany> productionCompanies = this.productionCompanies.stream()
                .map(ProductionCompaniesEntity::toDomainModel)
                .collect(Collectors.toList());

        List<Seasons> seasonsList = this.seasons.stream()
                .map(SeasonsEntity::toDomainModel)
                .toList();

        List<SpokenLanguages> spokenLanguagesList = this.spokenLanguages.stream()
                .map(SpokenLanguagesEntity::toDomainModel)
                .toList();

        return Optional.of(new TvSeries(
                id,
                backdropPath,
                createdSeries,
                firstAirDate,
                genreList,
                homePage,
                inProduction,
                lastAirDate,
                lastEpisodeList,
                name,
                nextEpisodeList,
                networksList,
                numberOfEpisodes,
                numberOfSeasons,
                originCountryList,
                originalLanguage,
                originalName,
                overview,
                popularity,
                posterPath,
                productionCompanies,
                productionCountries,
                seasonsList,
                spokenLanguagesList,
                status,
                tagline,
                voteAverage,
                voteCount
        ));
    }
}
