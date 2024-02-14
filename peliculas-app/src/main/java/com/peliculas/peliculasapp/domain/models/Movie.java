package com.peliculas.peliculasapp.domain.models;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


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

    private int revenue;

    private int budget;

    private float popularity;

    private String posterPath;

    private Date releaseDate;

    public Movie(long id, String overview, String status, List<ProductionCompany> productionCompanies, List<Genre> genres, List<ProductionCountries> productionCountries, String title, float voteAverage, int voteCount, int revenue, int budget, float popularity, String posterPath, Date releaseDate) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<ProductionCountries> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountries> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
