package com.peliculas.peliculasapp.application.ports.in;

import com.peliculas.peliculasapp.dto.MovieDTO;

public interface GetAndSaveInfoUseCase {
    MovieDTO getAndSaveMovieInfo(long movieId);
    void getAndSaveTvSeriesInfo(long tvSeriesId);
}
