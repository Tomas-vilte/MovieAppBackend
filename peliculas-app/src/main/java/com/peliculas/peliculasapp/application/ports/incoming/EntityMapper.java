package com.peliculas.peliculasapp.application.ports.incoming;

public interface EntityMapper<E, D> {
    D toDomainModel(E entity);
    E toEntity(D model);
}