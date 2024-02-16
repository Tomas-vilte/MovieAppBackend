package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String overview;

    private String status;

    @OneToMany
    private List<ProductionCompaniesEntity> productionCompanies;

    @OneToMany(cascade = CascadeType.ALL)
    private List<GenreEntity> genres;

    @OneToMany
    private List<ProductionCountriesEntity> productionCountries;

    private String title;

    private float voteAverage;

    private int voteCount;

    private int revenue;

    private int budget;

    private float popularity;

    private String posterPath;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    public MovieEntity() {}

    public MovieEntity(long id, String overview, String status, List<ProductionCompaniesEntity> productionCompanies, List<GenreEntity> genres, List<ProductionCountriesEntity> productionCountries, String title, float voteAverage, int voteCount, int revenue, int budget, float popularity, String posterPath, Date releaseDate) {
        this.id = id;
        this.overview = overview;
        this.status = status;
        this.productionCompanies = productionCompanies;
        this.genres = genres;
        this.productionCountries = productionCountries;
        this.title = title;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.revenue = revenue;
        this.budget = budget;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
    }

    public static MovieEntity fromDomainModel(Movie movie) {
        List<ProductionCountriesEntity> productionCountriesEntities = movie.getProductionCountries().stream()
                .map(ProductionCountriesEntity::fromDomainModel)
                .toList();

        List<ProductionCompaniesEntity> productionCompanyEntities = movie.getProductionCompanies().stream()
                .map(ProductionCompaniesEntity::fromDomainModel)
                .toList();

        List<GenreEntity> genreEntities = movie.getGenres().stream()
                .map(GenreEntity::fromDomainModel)
                .toList();

        return new MovieEntity(
                movie.getId(),
                movie.getOverview(),
                movie.getStatus(),
                productionCompanyEntities,
                genreEntities,
                productionCountriesEntities,
                movie.getTitle(),
                movie.getVoteAverage(),
                movie.getVoteCount(),
                movie.getRevenue(),
                movie.getBudget(),
                movie.getPopularity(),
                movie.getPosterPath(),
                movie.getReleaseDate()
        );
    }

    public Movie toDomainModel() {
        List<ProductionCountries> productionCountries = this.productionCountries.stream()
                .map(ProductionCountriesEntity::toDomainModel)
                .collect(Collectors.toList());

        List<ProductionCompany> productionCompanies = this.productionCompanies.stream()
                .map(ProductionCompaniesEntity::toDomainModel)
                .collect(Collectors.toList());

        List<Genre> genreList = this.genres.stream()
                .map(GenreEntity::toDomainModel)
                .collect(Collectors.toList());

        return new Movie(
                id,
                overview,
                status,
                productionCompanies,
                genreList,
                productionCountries,
                title,
                voteAverage,
                voteCount,
                revenue,
                budget,
                popularity,
                posterPath,
                releaseDate
        );
    }
}