package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveTvSeriesInfoUseCase;
import com.peliculas.peliculasapp.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.dto.TvSeriesInfoDTO;
import org.springframework.stereotype.Service;

@Service
public class TvSeriesService {
    private final GetAndSaveTvSeriesInfoUseCase getAndSaveTvSeriesInfoUseCase;

    public TvSeriesService(GetAndSaveTvSeriesInfoUseCase getAndSaveTvSeriesInfoUseCase) {
        this.getAndSaveTvSeriesInfoUseCase = getAndSaveTvSeriesInfoUseCase;
    }

    public TvSeriesDTO saveTvSeriesInfo(long tvSeriesId) {
        return getAndSaveTvSeriesInfoUseCase.getAndSaveTvSeriesInfo(tvSeriesId);
    }

    public TvSeriesInfoDTO getTvSeriesInfoById(long tvSeriesId) {
        return getAndSaveTvSeriesInfoUseCase.getTvSeriesInfoById(tvSeriesId);
    }
}
