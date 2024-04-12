package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveTvSeriesInfoUseCase;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesRepositoryPort;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.domain.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.domain.dto.TvSeriesInfoDTO;
import com.peliculas.peliculasapp.infrastructure.adapter.externalservices.SeriesDetailsAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Optional;

@Service
public class GetAndSaveTvSeriesInfoUseCaseImpl implements GetAndSaveTvSeriesInfoUseCase {
    private final TvSeriesRepositoryPort tvSeriesRepositoryPort;
    private final SeriesDetailsAdapter seriesDetailsAdapter;
    private final ModelMapper modelMapper;
    private final ValueOperations<String, Object> valueOperations;

    @Autowired
    public GetAndSaveTvSeriesInfoUseCaseImpl(TvSeriesRepositoryPort tvSeriesRepositoryPort,
                                             SeriesDetailsAdapter seriesDetailsAdapter,
                                             ModelMapper modelMapper,
                                             ValueOperations<String, Object> valueOperations)
    {
        this.tvSeriesRepositoryPort = tvSeriesRepositoryPort;
        this.seriesDetailsAdapter = seriesDetailsAdapter;
        this.modelMapper = modelMapper;
        this.valueOperations = valueOperations;
    }
    @Override
    public TvSeriesDTO getAndSaveTvSeriesInfo(long tvSeriesId) {
        TvSeries tvSeries = seriesDetailsAdapter.getTvSeriesInfoById(tvSeriesId);
        tvSeriesRepositoryPort.saveTvSeries(tvSeries);
        return modelMapper.map(tvSeries, TvSeriesDTO.class);
    }

    @Override
    public TvSeriesInfoDTO getTvSeriesInfoById(long tvSeriesId) {
       TvSeriesInfoDTO tvSeriesInfoDTO = getTvSeriesFromCache(tvSeriesId);
       if (tvSeriesInfoDTO == null) {
           System.out.println("Serie no encontrada en cache. Buscando en la base de datos");
           tvSeriesInfoDTO = getTvSeriesFromDatabase(tvSeriesId);
           storeTvSeriesInCache(tvSeriesId, tvSeriesInfoDTO);
       }
       System.out.println("Serie encontrada en cache");
       return tvSeriesInfoDTO;
    }

    private TvSeriesInfoDTO getTvSeriesFromCache(long tvSeriesId) {
        System.out.println("Intenando obtener series en la cache....");
        return (TvSeriesInfoDTO) valueOperations.get("series:" + tvSeriesId);
    }

    private void storeTvSeriesInCache(long tvSeriesId, TvSeriesInfoDTO tvSeriesInfoDTO) {
        System.out.println("Guardando Serie en Cache...");
        valueOperations.set("series:" + tvSeriesId, tvSeriesInfoDTO, Duration.ofMinutes(1));
    }

    private TvSeriesInfoDTO getTvSeriesFromDatabase(long movieId) {
        Optional<TvSeries> optionalTvSeries = tvSeriesRepositoryPort.getTvSeriesById(movieId);
        if (optionalTvSeries.isPresent()) {
            System.out.println("Serie encontrada en la base de datos.");
            return optionalTvSeries.map(tvSeries -> modelMapper.map(optionalTvSeries, TvSeriesInfoDTO.class)).orElse(null);
        } else {
            System.out.println("Serie no encontrada en la base de datos.");
            return null;
        }
    }
}
