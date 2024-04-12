package com.peliculas.peliculasapp.infrastructure.adapter.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NetworksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String logo_path;
    private String name;
    private String origin_country;

    public NetworksEntity(String logo_path, String name, String origin_country) {
        this.logo_path = logo_path;
        this.name = name;
        this.origin_country = origin_country;
    }

    public NetworksEntity() {}

}
