package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.GenreEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreEntityMapper implements ListMapper<Genre, GenreEntity> {
    @Override
    public List<GenreEntity> fromDomainModel(List<Genre> genres) {
        return genres.stream()
                .map(genre -> new GenreEntity(genre.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Genre> toDomainModel(List<GenreEntity> genres) {
        return genres.stream()
                .map(genre -> new Genre(genre.getName()))
                .collect(Collectors.toList());
    }
}
