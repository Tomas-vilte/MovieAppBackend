package com.peliculas.peliculasapp.application.ports.incoming;

public interface EntityMapper<E, D> {
    E toEntity(D domain);
    D toDomainModel(E entity);
}
