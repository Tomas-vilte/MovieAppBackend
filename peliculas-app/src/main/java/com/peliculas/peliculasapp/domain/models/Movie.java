package com.peliculas.peliculasapp.domain.models;
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
}
