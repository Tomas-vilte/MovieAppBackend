package com.peliculas.peliculasapp.infrastructure.entities;


import com.peliculas.peliculasapp.domain.models.Genre;
import jakarta.persistence.*;

@Entity
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    public static GenreEntity fromDomainModel(Genre genre) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(genre.getId());
        genreEntity.setName(genre.getName());
        return genreEntity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre toDomainModel() {
        return new Genre();
    }
}
