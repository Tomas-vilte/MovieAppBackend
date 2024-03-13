package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class ProductionCountriesEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     private String name;
     private String iso31661;
     public ProductionCountriesEntity() {}

     public ProductionCountriesEntity(String name, String iso31661) {
         this.name = name;
         this.iso31661 = iso31661;
     }


    public static ProductionCountriesEntity fromDomainModel(ProductionCountries productionCountries) {
        return new ProductionCountriesEntity(
                productionCountries.getName(),
                productionCountries.getIso_3166_1()
        );
    }

    public ProductionCountries toDomainModel() {
        return new ProductionCountries(
                name,
                iso31661
        );
    }

}
