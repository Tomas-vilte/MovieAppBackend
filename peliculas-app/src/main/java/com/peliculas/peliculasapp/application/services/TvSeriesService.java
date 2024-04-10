package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveTvSeriesInfoUseCase;
import com.peliculas.peliculasapp.domain.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.domain.dto.TvSeriesInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TvSeriesService {
    private final GetAndSaveTvSeriesInfoUseCase getAndSaveTvSeriesInfoUseCase;

    @Autowired
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
