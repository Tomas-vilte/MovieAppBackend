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
}
