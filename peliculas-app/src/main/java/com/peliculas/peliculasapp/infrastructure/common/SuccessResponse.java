package com.peliculas.peliculasapp.infrastructure.common;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class SuccessResponse implements Serializable {
    private int status;
    private String message;

    private Object movie;

    public SuccessResponse(int status, String message, Object movie) {
        this.status = status;
        this.message = message;
        this.movie = movie;
    }
}
