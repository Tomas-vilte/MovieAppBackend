package com.peliculas.peliculasapp.application.ports.in;

public interface GetAndSaveInfoUseCase {
    void getAndSaveMovieInfo(long movieId);
    void getAndSaveTvSeriesInfo(long tvSeriesId);
}
