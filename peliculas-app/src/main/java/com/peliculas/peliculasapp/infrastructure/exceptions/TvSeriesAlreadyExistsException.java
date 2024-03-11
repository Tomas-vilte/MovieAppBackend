package com.peliculas.peliculasapp.infrastructure.exceptions;

public class TvSeriesAlreadyExistsException extends RuntimeException {
    public TvSeriesAlreadyExistsException(String message) {
        super(message);
    }
}
