package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveTvSeriesInfoUseCase;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesRepositoryPort;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.infrastructure.adapters.SeriesDetailsAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GetAndSaveTvSeriesInfoUseCaseImpl implements GetAndSaveTvSeriesInfoUseCase {
    private final TvSeriesRepositoryPort tvSeriesRepositoryPort;
    private final SeriesDetailsAdapter seriesDetailsAdapter;
    private final ModelMapper modelMapper;

    public GetAndSaveTvSeriesInfoUseCaseImpl(TvSeriesRepositoryPort tvSeriesRepositoryPort,
                                             SeriesDetailsAdapter seriesDetailsAdapter,
                                             ModelMapper modelMapper)
    {
        this.tvSeriesRepositoryPort = tvSeriesRepositoryPort;
        this.seriesDetailsAdapter = seriesDetailsAdapter;
        this.modelMapper = modelMapper;
    }
    @Override
    public TvSeriesDTO getAndSaveTvSeriesInfo(long tvSeriesId) {
        TvSeries tvSeries = seriesDetailsAdapter.getTvSeriesInfoById(tvSeriesId);
        tvSeriesRepositoryPort.saveTvSeries(tvSeries);
        return modelMapper.map(tvSeries, TvSeriesDTO.class);
    }
}
