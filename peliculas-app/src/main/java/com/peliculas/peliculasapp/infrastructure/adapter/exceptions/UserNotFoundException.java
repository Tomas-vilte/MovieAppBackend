package com.peliculas.peliculasapp.infrastructure.adapter.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
