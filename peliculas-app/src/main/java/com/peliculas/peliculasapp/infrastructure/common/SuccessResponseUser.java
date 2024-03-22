package com.peliculas.peliculasapp.infrastructure.common;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class SuccessResponseUser implements Serializable {
    private int status;
    private String message;
    private Object data;

    public SuccessResponseUser(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
