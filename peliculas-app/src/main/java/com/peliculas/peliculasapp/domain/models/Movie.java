package com.peliculas.peliculasapp.domain.models;
import lombok.Getter;
import lombok.Setter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Movie {
    private long id;

    private String overview;

    private String status;

    private List<ProductionCompany> productionCompanies;

    private List<Genre> genres;

    private List<ProductionCountries> productionCountries;

    private String title;

    private float voteAverage;

    private int voteCount;

    private long revenue;

    private int budget;

    private float popularity;

    private String posterPath;

    private Date releaseDate;

    public Movie(long id, String overview, String status, List<ProductionCompany> productionCompanies, List<Genre> genres, List<ProductionCountries> productionCountries, String title, float voteAverage, int voteCount, long revenue, int budget, float popularity, String posterPath, Date releaseDate) {
        this.id = id;
        this.overview = overview;
        this.status = status;
        this.productionCompanies = Collections.emptyList();
        this.genres = genres;
        this.productionCountries = Collections.emptyList();
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
