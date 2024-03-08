package com.peliculas.peliculasapp.infrastructure.exceptions;

public class TvSeriesNotFoundException extends RuntimeException{
    public TvSeriesNotFoundException(String message) {
        super(message);
    }
}
