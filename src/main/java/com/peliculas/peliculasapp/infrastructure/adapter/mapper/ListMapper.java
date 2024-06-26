package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import java.util.List;

public interface ListMapper<S, T>{
    List<T> fromDomainModel(List<S> sourceList);
    List<S> toDomainModel(List<T> sourceList);
}