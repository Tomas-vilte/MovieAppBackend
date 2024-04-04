package com.peliculas.peliculasapp.infrastructure.adapter.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreatedSeriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int gender;
    private String profilePath;

    public CreatedSeriesEntity() {}

    public CreatedSeriesEntity(String name, int gender, String profilePath) {
        this.name = name;
        this.gender = gender;
        this.profilePath = profilePath;
    }
}
