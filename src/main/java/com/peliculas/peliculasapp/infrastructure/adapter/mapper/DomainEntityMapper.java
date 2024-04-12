package com.peliculas.peliculasapp.infrastructure.adapter.mapper;

public interface DomainEntityMapper<S, T> {
    T fromDomainModel(S source);
    S toDomainModel(T target);
}
