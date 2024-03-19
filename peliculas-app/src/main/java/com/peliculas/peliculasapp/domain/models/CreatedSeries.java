package com.peliculas.peliculasapp.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class CreatedSeries implements Serializable {

    private String name;

    private int gender;

    private String profile_path;
}
