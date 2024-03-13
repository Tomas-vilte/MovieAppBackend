package com.peliculas.peliculasapp.dto;
import lombok.Data;


@Data
public class MovieDTO {
    private String title;
    private String overview;
    private String release_date;
    private float popularity;
}
