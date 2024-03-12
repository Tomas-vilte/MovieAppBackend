package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpokenLanguages {
    private String english_name;
    private String iso_639_1;
    private String name;
}
