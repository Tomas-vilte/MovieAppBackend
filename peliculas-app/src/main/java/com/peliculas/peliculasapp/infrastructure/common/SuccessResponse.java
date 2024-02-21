package com.peliculas.peliculasapp.infrastructure.common;
import com.peliculas.peliculasapp.domain.models.Movie;

public class SuccessResponse {
    private int status;
    private String message;

    public SuccessResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
