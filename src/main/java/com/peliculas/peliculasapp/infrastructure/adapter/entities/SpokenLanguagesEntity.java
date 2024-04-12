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
public class SpokenLanguagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String english_name;
    private String iso_639_1;
    private String name;

    public SpokenLanguagesEntity() {}

    public SpokenLanguagesEntity(String english_name, String iso_639_1, String name) {
        this.english_name = english_name;
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }
}
