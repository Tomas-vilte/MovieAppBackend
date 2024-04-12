package com.peliculas.peliculasapp.infrastructure.adapter.exceptions;

public class TvSeriesNotFoundException extends RuntimeException{
    public TvSeriesNotFoundException(String message) {
        super(message);
    }
}
