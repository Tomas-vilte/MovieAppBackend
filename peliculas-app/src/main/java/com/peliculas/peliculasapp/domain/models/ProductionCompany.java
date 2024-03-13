package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductionCompany {

    private String name;

    private String logo_path;

    private String origin_country;
}
