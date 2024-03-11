package com.peliculas.peliculasapp.infrastructure.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NextEpisodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
