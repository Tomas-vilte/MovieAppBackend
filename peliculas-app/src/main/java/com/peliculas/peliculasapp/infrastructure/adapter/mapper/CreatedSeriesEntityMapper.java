package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import com.peliculas.peliculasapp.domain.models.CreatedSeries;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.CreatedSeriesEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreatedSeriesEntityMapper implements ListMapper<CreatedSeries, CreatedSeriesEntity> {
    @Override
    public List<CreatedSeriesEntity> fromDomainModel(List<CreatedSeries> createdSeries) {
        return createdSeries.stream()
                .map(createBySeries -> new CreatedSeriesEntity(createBySeries.getName(), createBySeries.getGender(), createBySeries.getProfile_path()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CreatedSeries> toDomainModel(List<CreatedSeriesEntity> createdSeriesEntities) {
        return createdSeriesEntities.stream()
                .map(createBySeries -> new CreatedSeries(createBySeries.getName(), createBySeries.getGender(), createBySeries.getProfilePath()))
                .collect(Collectors.toList());
    }
}
