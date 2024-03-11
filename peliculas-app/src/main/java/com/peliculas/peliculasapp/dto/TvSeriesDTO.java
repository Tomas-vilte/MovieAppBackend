package com.peliculas.peliculasapp.dto;
import lombok.Data;

@Data
public class TvSeriesDTO {
    private String name;
    private String overview;
    private int number_of_episodes;
    private int number_of_seasons;
    private float popularity;
}
