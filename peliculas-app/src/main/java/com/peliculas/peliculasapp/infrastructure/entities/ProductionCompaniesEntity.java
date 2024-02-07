package com.peliculas.peliculasapp.infrastructure.entities;


import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductionCompaniesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String logoPath;

    private String originCountry;


    public static ProductionCompaniesEntity fromDomainModel(ProductionCompany productionCompany) {
        return new ProductionCompaniesEntity();
    }

    public ProductionCompany toDomainModel() {
        return new ProductionCompany();
    }

}
