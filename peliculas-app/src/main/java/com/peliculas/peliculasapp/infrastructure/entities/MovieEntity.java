package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Setter
@Getter
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long movieId;

    @Column(length = 1500)
    private String overview;

    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<ProductionCompaniesEntity> productionCompanies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<GenreEntity> genres;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
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
}
