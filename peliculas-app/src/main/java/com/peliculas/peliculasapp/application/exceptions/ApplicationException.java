package com.peliculas.peliculasapp.application.exceptions;

public abstract class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }
}
