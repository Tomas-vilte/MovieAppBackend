package com.peliculas.peliculasapp.application.ports.in;
import com.peliculas.peliculasapp.domain.dto.MovieDTO;
import com.peliculas.peliculasapp.domain.dto.MovieInfoDTO;

public interface GetAndSaveMovieInfoUseCase {
    MovieDTO getAndSaveMovieInfo(long movieId);
    MovieInfoDTO getMovieById(long movieId);
}
