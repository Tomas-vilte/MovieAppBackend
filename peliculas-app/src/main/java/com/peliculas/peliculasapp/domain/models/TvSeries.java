package com.peliculas.peliculasapp.domain.models;
import java.util.Date;
import java.util.List;

public class TvSeries {

    private long id;

    private String backdropPath;

    private Date firstAirDate;

    private Date lastAirDate;

    private String homePage;

    private boolean inProduction;

    private float voteAverage;

    private int voteCount;

    private List<Seasons> seasons;

    private String tagLine;

    private List<ProductionCountries> productionCountries;

    private List<ProductionCompany> productionCompanyList;
    private List<Genre> genreList;

    private float popularity;

    private String posterPath;

    private String overview;

    private String originalName;

    private int numberOfEpisodes;

    private int numberOfSeasons;

    private String status;
}
