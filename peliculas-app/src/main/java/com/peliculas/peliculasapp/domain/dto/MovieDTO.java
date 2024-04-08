package com.peliculas.peliculasapp.domain.dto;
import lombok.Data;


@Data
public class MovieDTO {
    private long id;
    private String title;
    private String overview;
    private String release_date;
    private float popularity;
}
