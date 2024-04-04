package com.peliculas.peliculasapp.infrastructure.adapter.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
