package com.peliculas.peliculasapp.infrastructure.common;

public class ErrorResponse {
    private int status;
    private String message;
    private String description;

    public ErrorResponse(int status, String message, String description) {
        this.status = status;
        this.message = message;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

