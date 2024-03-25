package com.peliculas.peliculasapp.domain.models;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class MovieReview {
    private long id;
    private Movie movie;
    private User user;
    private String reviewText;
    private int rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
