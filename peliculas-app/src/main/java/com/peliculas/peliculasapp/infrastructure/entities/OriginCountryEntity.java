package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;


@Entity
public class OriginCountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public OriginCountryEntity() {}

    public OriginCountryEntity(String name) {
        this.name = name;
    }

    public String toDomainModel() {
        return this.name;
    }

    public static OriginCountryEntity fromDomainModel(String country) {
        return new OriginCountryEntity(country);
    }
}
