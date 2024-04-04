package com.peliculas.peliculasapp.infrastructure.adapter.exceptions;

public class MovieAlreadyExistsException extends RuntimeException{
    public MovieAlreadyExistsException(String message) {
        super(message);
    }
}
