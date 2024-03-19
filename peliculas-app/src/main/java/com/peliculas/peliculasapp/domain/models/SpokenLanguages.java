package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class SpokenLanguages implements Serializable {
    private String english_name;
    private String iso_639_1;
    private String name;
}
