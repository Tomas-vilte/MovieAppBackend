package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Seasons implements Serializable {
    private String air_date;

    private int episode_count;

    private String name;

    private String overview;

    private String poster_path;

    private int season_number;

    private float vote_average;
}
