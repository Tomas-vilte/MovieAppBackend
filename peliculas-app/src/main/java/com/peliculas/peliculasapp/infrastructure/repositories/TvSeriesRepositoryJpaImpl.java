package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesRepositoryPort;
import com.peliculas.peliculasapp.infrastructure.entities.TvSeriesEntity;
import com.peliculas.peliculasapp.infrastructure.exceptions.TvSeriesAlreadyExistsException;
import com.peliculas.peliculasapp.infrastructure.exceptions.TvSeriesNotFoundException;
import com.peliculas.peliculasapp.infrastructure.mapper.TvSeriesEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class TvSeriesRepositoryJpaImpl implements TvSeriesRepositoryPort {
    private final TvSeriesRepository tvSeriesRepositoryJpa;
    private final TvSeriesEntityMapper tvSeriesEntityMapper;

    @Autowired
    public TvSeriesRepositoryJpaImpl(TvSeriesRepository tvSeriesRepositoryJpa, TvSeriesEntityMapper tvSeriesEntityMapper) {
        this.tvSeriesRepositoryJpa = tvSeriesRepositoryJpa;
        this.tvSeriesEntityMapper = tvSeriesEntityMapper;
    }
    @Override
    public Optional<TvSeries> saveTvSeries(TvSeries tvSeries) {
        try {
          if (tvSeriesRepositoryJpa.existsByName(tvSeries.getName())) {
              throw new TvSeriesAlreadyExistsException("Esta serie ya se encuentra guardada");
          }
            TvSeriesEntity tvSeriesEntity = tvSeriesEntityMapper.fromDomainModel(tvSeries);
            TvSeriesEntity savedTvSeriesEntity = tvSeriesRepositoryJpa.save(tvSeriesEntity);
            return Optional.of(tvSeriesEntityMapper.toDomainModel(savedTvSeriesEntity));
        } catch (TvSeriesAlreadyExistsException e) {
            throw new TvSeriesAlreadyExistsException("Esta serie ya se encuentra guardada");
        }
    }

    public Optional<TvSeries> getTvSeriesInfo(long tvSeriesId) {
        Optional<TvSeriesEntity> tvSeriesEntity = tvSeriesRepositoryJpa.findById(tvSeriesId);
        return Optional.of(tvSeriesEntity.map(tvSeriesEntityMapper::toDomainModel)
                .orElseThrow(() -> new TvSeriesNotFoundException("Serie no encontrada con el ID: " + tvSeriesId)));
    }

    @Override
    public Optional<TvSeries> getTvSeriesById(long tvSeriesId) {
        Optional<TvSeriesEntity> tvSeriesEntity = tvSeriesRepositoryJpa.findById(tvSeriesId);
        return Optional.of(tvSeriesEntity.map(tvSeriesEntityMapper::toDomainModel)
                .orElseThrow(() -> new TvSeriesNotFoundException("Serie no encontrada con el ID: " + tvSeriesId)));
    }
}
