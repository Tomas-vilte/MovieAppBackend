package com.peliculas.peliculasapp.domain.models;
import lombok.Data;
@Data
public class MovieReview {
    private User movie;
    private Movie user;
    private String reviewText;
    private int rating;
}