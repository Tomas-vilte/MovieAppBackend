package com.peliculas.peliculasapp.domain.dto;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class MovieInfoDTO implements Serializable {
    private String title;
    private String homepage;
    private String overview;
    private String release_date;
    private float popularity;
    private String status;
    private List<ProductionCompany> production_companies;
    private List<ProductionCountries> production_countries;
    private List<Genre> genres;
    private float vote_average;
    private float vote_count;
    private long revenue;
    private int budget;
    private String poster_path;
}
