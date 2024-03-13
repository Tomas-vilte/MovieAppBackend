package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.Genre;
import jakarta.persistence.*;

@Entity
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public GenreEntity() {}

    public GenreEntity(String name) {
        this.name = name;
    }

    public static GenreEntity fromDomainModel(Genre genre) {
        return new GenreEntity(
                genre.getName()
        );
    }
    public Genre toDomainModel() {
        return new Genre(
                name
        );
    }
}