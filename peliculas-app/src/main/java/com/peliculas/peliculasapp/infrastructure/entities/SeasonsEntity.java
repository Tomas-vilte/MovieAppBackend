package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    public SeasonsEntity() {}

    public SeasonsEntity(String airDate, int episodeCount, String name, String overview, String posterPath, int seasonNumber, float voteAverage) {
        this.airDate = airDate;
        this.episodeCount = episodeCount;
        this.name = name;
        this.overview = overview;
        this.posterPath = posterPath;
        this.seasonNumber = seasonNumber;
        this.voteAverage = voteAverage;
    }
}
