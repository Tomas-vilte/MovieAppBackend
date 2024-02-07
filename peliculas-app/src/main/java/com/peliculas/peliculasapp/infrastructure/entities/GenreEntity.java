package com.peliculas.peliculasapp.infrastructure.entities;


import com.peliculas.peliculasapp.domain.models.Genre;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public static GenreEntity fromDomainModel(Genre genre) {
        return new GenreEntity();
    }

    public Genre toDomainModel() {
        return new Genre();
    }
}
