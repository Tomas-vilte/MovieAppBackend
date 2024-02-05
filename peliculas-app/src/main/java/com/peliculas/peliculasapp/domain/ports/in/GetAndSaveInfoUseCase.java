package com.peliculas.peliculasapp.domain.ports.in;

public interface GetAndSaveInfoUseCase {
    void getAndSaveMovieInfo(long movieId);
    void getAndSaveTvSeriesInfo(long tvSeriesId);
}
