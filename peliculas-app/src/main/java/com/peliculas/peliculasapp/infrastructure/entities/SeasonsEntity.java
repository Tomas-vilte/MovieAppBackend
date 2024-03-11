package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.Entity;

@Entity
public class SeasonsEntity {
    private String airDate;

    private int episodeCount;

    private long id;

    private String name;

    private String overview;

    private String posterPath;

    private int seasonNumber;

    private float voteAverage;
}
