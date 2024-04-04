package com.peliculas.peliculasapp.infrastructure.adapter.exceptions;

public class MovieReviewNotFoundException extends RuntimeException {
    public MovieReviewNotFoundException(String message) {
        super(message);
    }
}
