package com.peliculas.peliculasapp.infrastructure.mapper;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.infrastructure.entities.GenreEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreEntityMapper {
    public List<GenreEntity> fromDomainModel(List<Genre> genres) {
        return genres.stream()
                .map(genre -> new GenreEntity(genre.getName()))
                .collect(Collectors.toList());
    }

    public List<Genre> toDomainModel(List<GenreEntity> genres) {
        return genres.stream()
                .map(genre -> new Genre(genre.getName()))
                .collect(Collectors.toList());
    }
}
