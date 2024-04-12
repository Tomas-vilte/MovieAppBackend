package com.peliculas.peliculasapp.application.mapper;
import com.peliculas.peliculasapp.domain.dto.MovieDTO;
import com.peliculas.peliculasapp.domain.models.Movie;
import org.springframework.stereotype.Component;


@Component
public class MovieDtoMapper {
    public MovieDTO toDto(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setOverview(movie.getOverview());
        dto.setRelease_date(movie.getRelease_date());
        dto.setPopularity(movie.getPopularity());
        return dto;
    }
}
