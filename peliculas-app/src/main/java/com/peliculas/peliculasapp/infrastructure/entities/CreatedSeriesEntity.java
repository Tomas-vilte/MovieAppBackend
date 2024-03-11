package com.peliculas.peliculasapp.infrastructure.entities;

import jakarta.persistence.Entity;

@Entity
public class CreatedSeriesEntity {

    private long id;

    private long credit_id;

    private String name;

    private int gender;

    private String profile_path;

}
