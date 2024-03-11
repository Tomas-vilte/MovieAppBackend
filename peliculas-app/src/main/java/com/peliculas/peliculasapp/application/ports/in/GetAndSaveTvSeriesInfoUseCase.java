package com.peliculas.peliculasapp.application.ports.in;
import com.peliculas.peliculasapp.dto.TvSeriesDTO;

public interface GetAndSaveTvSeriesInfoUseCase {
    TvSeriesDTO getAndSaveTvSeriesInfo(long tvSeriesId);
}
