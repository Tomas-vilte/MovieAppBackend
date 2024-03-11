package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SeasonsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String airDate;

    private int episodeCount;

    private String name;

    private String overview;

    private String posterPath;

    private int seasonNumber;

    private float voteAverage;
}
