package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Networks implements Serializable {
    private String logo_path;
    private String name;
    private String origin_country;
}
