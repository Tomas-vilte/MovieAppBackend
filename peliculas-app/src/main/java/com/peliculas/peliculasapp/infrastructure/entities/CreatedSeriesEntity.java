package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.CreatedSeries;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CreatedSeriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int gender;
    private String profilePath;

    public static CreatedSeriesEntity fromDomainModel(CreatedSeries createdSeries) {
        return new CreatedSeriesEntity();
    }

    public CreatedSeries toDomainModel() {
        return new CreatedSeries();
    }

}
