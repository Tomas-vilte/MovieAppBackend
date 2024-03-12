package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesRepositoryPort;
import com.peliculas.peliculasapp.infrastructure.entities.TvSeriesEntity;
import com.peliculas.peliculasapp.infrastructure.exceptions.TvSeriesAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class TvSeriesRepositoryJpaImpl implements TvSeriesRepositoryPort {
    private final TvSeriesRepository tvSeriesRepositoryJpa;

    @Autowired
    public TvSeriesRepositoryJpaImpl(TvSeriesRepository tvSeriesRepositoryJpa) {
        this.tvSeriesRepositoryJpa = tvSeriesRepositoryJpa;
    }
    @Override
    public Optional<TvSeries> saveTvSeries(TvSeries tvSeries) {
        try {
          if (tvSeriesRepositoryJpa.existsById(tvSeries.getId())) {
              throw new TvSeriesAlreadyExistsException("Esta serie ya se encuentra guardada");
          }
          TvSeriesEntity tvSeriesEntity = TvSeriesEntity.fromDomainModel(tvSeries);
          TvSeriesEntity savedTvSeries = tvSeriesRepositoryJpa.save(tvSeriesEntity);
          return savedTvSeries.toDomainModel();
        } catch (TvSeriesAlreadyExistsException e) {
            throw new TvSeriesAlreadyExistsException("Esta serie ya se encuentra guardada");
        }
    }
}
