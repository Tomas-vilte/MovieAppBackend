package com.peliculas.peliculasapp.application.ports.in;
import com.peliculas.peliculasapp.domain.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.domain.dto.TvSeriesInfoDTO;

public interface GetAndSaveTvSeriesInfoUseCase {
    TvSeriesDTO getAndSaveTvSeriesInfo(long tvSeriesId);

    TvSeriesInfoDTO getTvSeriesInfoById(long tvSeriesId);
}
