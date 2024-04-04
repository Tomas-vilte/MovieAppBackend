package com.peliculas.peliculasapp.infrastructure.adapter.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public GenreEntity() {}

    public GenreEntity(String name) {
        this.name = name;
    }
}