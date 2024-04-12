package com.peliculas.peliculasapp.infrastructure.adapter.exceptions;

public class TvSeriesAlreadyExistsException extends RuntimeException {
    public TvSeriesAlreadyExistsException(String message) {
        super(message);
    }
}
