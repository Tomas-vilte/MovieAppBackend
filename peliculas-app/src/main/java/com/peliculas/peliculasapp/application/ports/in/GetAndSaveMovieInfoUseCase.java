package com.peliculas.peliculasapp.application.ports.in;

import com.peliculas.peliculasapp.dto.MovieDTO;

public interface GetAndSaveMovieInfoUseCase {
    MovieDTO getAndSaveMovieInfo(long movieId);
}
