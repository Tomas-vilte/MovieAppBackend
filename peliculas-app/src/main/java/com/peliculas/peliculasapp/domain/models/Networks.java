package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class Networks implements Serializable {
    private String logo_path;
    private String name;
    private String origin_country;
}
