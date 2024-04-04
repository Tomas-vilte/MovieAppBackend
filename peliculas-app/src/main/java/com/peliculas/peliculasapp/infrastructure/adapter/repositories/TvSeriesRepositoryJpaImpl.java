package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesRepositoryPort;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.TvSeriesEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.mapper.TvSeriesEntityMapper;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.TvSeriesAlreadyExistsException;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.TvSeriesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
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
