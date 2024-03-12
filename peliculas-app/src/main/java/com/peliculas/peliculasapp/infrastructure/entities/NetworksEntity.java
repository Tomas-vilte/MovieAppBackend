package com.peliculas.peliculasapp.infrastructure.entities;
import com.peliculas.peliculasapp.domain.models.Networks;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
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

    public Networks toDomainModel() {
        return new Networks(
                logo_path,
                name,
                origin_country
        );
    }

    public static NetworksEntity fromDomainModel(Networks networks) {
        return new NetworksEntity(
                networks.getLogo_path(),
                networks.getName(),
                networks.getOrigin_country()
        );
    }
}
