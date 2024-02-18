package com.peliculas.peliculasapp.infrastructure.exceptions;

public abstract class InfrastructureException extends RuntimeException{
    public InfrastructureException(String message) {
        super(message);
    }
}
