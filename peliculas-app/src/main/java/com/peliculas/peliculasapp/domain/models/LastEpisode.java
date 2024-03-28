package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastEpisode implements Serializable {
    private int id;
    private String name;
    private String overview;
    private float vote_count;
    private String air_date;
    private int episode_number;
    private String episode_type;
    private String production_code;
    private int runtime;
    private int season_number;
    private int show_id;
    private String still_path;
}
