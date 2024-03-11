package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.Entity;

@Entity
public class SpokenLanguagesEntity {
    private long id;
    private String english_name;
    private String iso_639_1;
    private String name;
}
