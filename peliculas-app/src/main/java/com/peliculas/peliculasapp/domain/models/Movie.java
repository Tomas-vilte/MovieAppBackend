package com.peliculas.peliculasapp.domain.models;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Movie {
    private long id;

    private String overview;

    private String status;

    private List<ProductionCompany> production_companies;

    private List<Genre> genres;

    private List<ProductionCountries> production_countries;

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
        this.production_companies = productionCompanies;
        this.genres = genres;
        this.production_countries = productionCountries;
        this.title = title;
        this.vote_average = voteAverage;
        this.vote_count = voteCount;
        this.revenue = revenue;
        this.budget = budget;
        this.popularity = popularity;
        this.poster_path = posterPath;
        this.release_date = releaseDate;
    }

    public static Movie ofTesting() {
        long id = 1;
        String overview = "Descripcion de la pelicula";
        String status = "Estado de la pelcula";
        List<ProductionCompany> productionCompanies = new ArrayList<>();

        List<Genre> genres = new ArrayList<>();

        List<ProductionCountries> productionCountries = new ArrayList<>();

        String title = "ttulo de la pelicula";
        float voteAverage = 4.5f;
        float voteCount = 1000;
        long revenue = 1000000;
        int budget = 500000;
        String posterPath = "/poster.jpg";
        float popularity = 7.8f;
        String releaseDate = "2024-03-18";

        return new Movie(id, overview, status, productionCompanies, genres, productionCountries, title, voteAverage, voteCount, revenue, budget, popularity, posterPath, releaseDate);
    }

}
