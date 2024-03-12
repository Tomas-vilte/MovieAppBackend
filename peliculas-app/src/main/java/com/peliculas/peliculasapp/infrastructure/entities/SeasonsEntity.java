package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.Seasons;
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

    public Seasons toDomainModel() {
        return new Seasons(
                airDate,
                episodeCount,
                name,
                overview,
                posterPath,
                seasonNumber,
                voteAverage
        );
    }

    public static SeasonsEntity fromDomainModel(Seasons seasons) {
        return new SeasonsEntity(
                seasons.getAir_date(),
                seasons.getEpisode_count(),
                seasons.getName(),
                seasons.getOverview(),
                seasons.getPoster_path(),
                seasons.getSeason_number(),
                seasons.getVote_average()
        );
    }
}
