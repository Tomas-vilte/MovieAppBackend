package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.LastEpisode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LastEpisodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public LastEpisodeEntity() {}

    public LastEpisodeEntity(String name, String overview, float vote_count, String air_date, int episode_number, String episode_type, String production_code, int runtime, int season_number, int show_id, String still_path) {
        this.name = name;
        this.overview = overview;
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

    public LastEpisode toDomainModel() {
        return new LastEpisode(
                id,
                name,
                overview,
                vote_count,
                air_date,
                episode_number,
                episode_type,
                production_code,
                runtime,
                season_number,
                show_id,
                still_path
        );
    }
    public static LastEpisodeEntity fromDomainModel(LastEpisode lastEpisode) {
        return new LastEpisodeEntity(
                lastEpisode.getName(),
                lastEpisode.getOverview(),
                lastEpisode.getVote_count(),
                lastEpisode.getAir_date(),
                lastEpisode.getEpisode_number(),
                lastEpisode.getEpisode_type(),
                lastEpisode.getProduction_code(),
                lastEpisode.getRuntime(),
                lastEpisode.getSeason_number(),
                lastEpisode.getShow_id(),
                lastEpisode.getStill_path()
        );
    }
}
