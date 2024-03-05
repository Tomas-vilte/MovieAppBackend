package com.peliculas.peliculasapp.dto;
import com.peliculas.peliculasapp.domain.models.Genre;
import lombok.Data;
import java.util.List;


@Data
public class MovieDTO {
    private String title;
    private String overview;
    private String release_date;
    private List<Genre> genres;
    private float popularity;
}
