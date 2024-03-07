package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.Genre;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public static GenreEntity fromDomainModel(Genre genre) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(genre.getName());
        return genreEntity;
    }
    public Genre toDomainModel() {
        return new Genre();
    }
}