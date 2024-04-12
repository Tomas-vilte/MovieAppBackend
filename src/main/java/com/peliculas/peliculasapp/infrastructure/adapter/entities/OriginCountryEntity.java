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
public class OriginCountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public OriginCountryEntity() {}

    public OriginCountryEntity(String name) {
        this.name = name;
    }
}
