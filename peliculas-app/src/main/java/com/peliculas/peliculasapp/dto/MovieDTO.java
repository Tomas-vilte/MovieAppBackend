package com.peliculas.peliculasapp.dto;
import com.peliculas.peliculasapp.domain.models.Genre;
import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDTO {
    private String title;
    private String overview;
    private Date release_date;
    private List<Genre> genres;
    private float popularity;
}
