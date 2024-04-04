package com.peliculas.peliculasapp.domain.dto;
import lombok.Data;

@Data
public class TvSeriesDTO {
    private String name;
    private String overview;
    private int number_of_episodes;
    private int number_of_seasons;
    private float popularity;
}
