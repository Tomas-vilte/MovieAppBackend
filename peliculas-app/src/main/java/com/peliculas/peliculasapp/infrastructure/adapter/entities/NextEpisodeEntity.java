package com.peliculas.peliculasapp.infrastructure.adapter.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NextEpisodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(length = 1000)
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

    public NextEpisodeEntity() {}

    public NextEpisodeEntity(String name, String overview, float vote_average, int vote_count, String air_date, int episode_number, String episode_type, String production_code, int runtime, int season_number, int show_id, String still_path) {
        this.name = name;
        this.overview = overview;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.air_date = air_date;
        this.episode_number = episode_number;
        this.episode_type = episode_type;
        this.production_code = production_code;
        this.runtime = runtime;
        this.season_number = season_number;
        this.show_id = show_id;
        this.still_path = still_path;
    }
}
