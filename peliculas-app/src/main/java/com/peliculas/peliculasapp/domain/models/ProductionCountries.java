package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionCountries implements Serializable {
    private String name;
    private String iso_3166_1;

}
