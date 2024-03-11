package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CreatedSeriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long credit_id;

    private String name;

    private int gender;

    private String profile_path;

}
