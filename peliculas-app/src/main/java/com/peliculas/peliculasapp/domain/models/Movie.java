package com.peliculas.peliculasapp.domain.models;
import lombok.Getter;
import lombok.Setter;
import java.util.Collections;
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

    private float vote_average;

    private float vote_count;

    private long revenue;

    private int budget;

    private float popularity;

    private String poster_path;

    private String release_date;

    public Movie(long id, String overview, String status, List<ProductionCompany> productionCompanies, List<Genre> genres, List<ProductionCountries> productionCountries, String title, float voteAverage, float voteCount, long revenue, int budget, float popularity, String posterPath, String releaseDate) {
        this.id = id;
        this.overview = overview;
        this.status = status;
        this.productionCompanies = Collections.emptyList();
        this.genres = genres;
        this.productionCountries = Collections.emptyList();
        this.title = title;
        this.vote_average = voteAverage;
        this.vote_count = voteCount;
        this.revenue = revenue;
        this.budget = budget;
        this.popularity = popularity;
        this.poster_path = posterPath;
        this.release_date = releaseDate;
    }
}
