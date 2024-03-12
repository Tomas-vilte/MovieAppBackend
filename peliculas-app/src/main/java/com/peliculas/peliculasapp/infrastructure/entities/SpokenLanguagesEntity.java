package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.SpokenLanguages;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SpokenLanguagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String english_name;
    private String iso_639_1;
    private String name;

    public SpokenLanguagesEntity(String english_name, String iso_639_1, String name) {
        this.english_name = english_name;
        this.iso_639_1 = iso_639_1;
        this.name = name;
    }

    public SpokenLanguages toDomainModel() {
        return new SpokenLanguages(
                english_name,
                iso_639_1,
                name
        );
    }

    public SpokenLanguagesEntity() {}


    public static SpokenLanguagesEntity fromDomainModel(SpokenLanguages spokenLanguages) {
        return new SpokenLanguagesEntity(
                spokenLanguages.getEnglish_name(),
                spokenLanguages.getIso_639_1(),
                spokenLanguages.getName()
        );
    }
}
