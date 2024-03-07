package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long movieId;

    @Column(length = 1500)
    private String overview;

    private String status;

    @OneToMany
    private List<ProductionCompaniesEntity> productionCompanies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private List<GenreEntity> genres;

    @OneToMany
    private List<ProductionCountriesEntity> productionCountries;

    private String title;

    private float voteAverage;

    private float voteCount;

    private long revenue;

    private int budget;

    private float popularity;

    private String posterPath;

    private String releaseDate;

    public MovieEntity() {}

    public MovieEntity(long movieId, String overview, String status, List<ProductionCompaniesEntity> productionCompanies, List<GenreEntity> genres, List<ProductionCountriesEntity> productionCountries, String title, float voteAverage, float voteCount, long revenue, int budget, float popularity, String posterPath, String releaseDate) {
        this.movieId = movieId;
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
                movie.getVote_average(),
                movie.getVote_count(),
                movie.getRevenue(),
                movie.getBudget(),
                movie.getPopularity(),
                movie.getPoster_path(),
                movie.getRelease_date()
        );
    }

    public Optional<Movie> toDomainModel() {
        List<ProductionCountries> productionCountries = this.productionCountries.stream()
                .map(ProductionCountriesEntity::toDomainModel)
                .collect(Collectors.toList());

        List<ProductionCompany> productionCompanies = this.productionCompanies.stream()
                .map(ProductionCompaniesEntity::toDomainModel)
                .collect(Collectors.toList());

        List<Genre> genreList = this.genres.stream()
                .map(GenreEntity::toDomainModel)
                .collect(Collectors.toList());

        return Optional.of(new Movie(
                movieId,
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
        ));
    }
}
