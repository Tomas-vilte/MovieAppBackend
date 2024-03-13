package com.peliculas.peliculasapp.application.ports.in;
import com.peliculas.peliculasapp.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.dto.TvSeriesInfoDTO;

public interface GetAndSaveTvSeriesInfoUseCase {
    TvSeriesDTO getAndSaveTvSeriesInfo(long tvSeriesId);

    TvSeriesInfoDTO getTvSeriesInfoById(long tvSeriesId);
}
