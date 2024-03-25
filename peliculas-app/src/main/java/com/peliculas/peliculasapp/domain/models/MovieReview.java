package com.peliculas.peliculasapp.domain.models;
import com.peliculas.peliculasapp.infrastructure.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieReview {
    private long id;
    private MovieEntity movie;
    private UserEntity user;
    private String reviewText;
    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
