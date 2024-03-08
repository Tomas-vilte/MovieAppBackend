package com.peliculas.peliculasapp.domain.models;
import lombok.Data;

@Data
public class ProductionCompany {
    private long id;

    private String name;

    private String logo_path;

    private String origin_country;
}
