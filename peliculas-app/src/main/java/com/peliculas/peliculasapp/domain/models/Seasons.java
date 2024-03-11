package com.peliculas.peliculasapp.domain.models;
import lombok.Data;

@Data
public class Seasons {
    private String air_date;

    private int episode_count;

    private long id;

    private String name;

    private String overview;

    private String poster_path;

    private int season_number;

    private float vote_average;
}
