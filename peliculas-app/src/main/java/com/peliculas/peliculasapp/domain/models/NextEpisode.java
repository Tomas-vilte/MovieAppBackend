package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NextEpisode {
    private int id;
    private String name;
    private String overview;
    private float vote_average;
    private int vote_count;
    private String air_date;
    private int episode_number;
    private String episode_type;
    private String production_code;
    private int runtime;
    private int season_number;
    private int show_id;
    private String still_path;
}
