package com.peliculas.peliculasapp.domain.exceptions;

public abstract class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }
}
