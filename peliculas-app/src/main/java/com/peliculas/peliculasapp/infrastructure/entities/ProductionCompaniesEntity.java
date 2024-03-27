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
public class ProductionCompaniesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String logoPath;

    private String originCountry;

    public ProductionCompaniesEntity() {}

    public ProductionCompaniesEntity(String name, String logoPath, String originCountry) {
        this.name = name;
        this.logoPath = logoPath;
        this.originCountry = originCountry;
    }
}
