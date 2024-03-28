package com.peliculas.peliculasapp.infrastructure.mapper;
import com.peliculas.peliculasapp.domain.models.NextEpisode;
import com.peliculas.peliculasapp.infrastructure.entities.NextEpisodeEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NextEpisodeEntityMapper implements ListMapper<NextEpisode, NextEpisodeEntity>{
    @Override
    public List<NextEpisodeEntity> fromDomainModel(List<NextEpisode> nextEpisodesList) {
        return nextEpisodesList.stream()
                .map(nextEpisode -> new NextEpisodeEntity(
                        nextEpisode.getName(),
                        nextEpisode.getOverview(),
                        nextEpisode.getVote_average(),
                        nextEpisode.getVote_count(),
                        nextEpisode.getAir_date(),
                        nextEpisode.getEpisode_number(),
                        nextEpisode.getEpisode_type(),
                        nextEpisode.getProduction_code(),
                        nextEpisode.getRuntime(),
                        nextEpisode.getSeason_number(),
                        nextEpisode.getShow_id(),
                        nextEpisode.getStill_path()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<NextEpisode> toDomainModel(List<NextEpisodeEntity> nextEpisodeEntityList) {
        return nextEpisodeEntityList.stream()
                .map(nextEpisodeEntity -> new NextEpisode(
                        nextEpisodeEntity.getId(),
                        nextEpisodeEntity.getName(),
                        nextEpisodeEntity.getOverview(),
                        nextEpisodeEntity.getVote_average(),
                        nextEpisodeEntity.getVote_count(),
                        nextEpisodeEntity.getAir_date(),
                        nextEpisodeEntity.getEpisode_number(),
                        nextEpisodeEntity.getEpisode_type(),
                        nextEpisodeEntity.getProduction_code(),
                        nextEpisodeEntity.getRuntime(),
                        nextEpisodeEntity.getSeason_number(),
                        nextEpisodeEntity.getShow_id(),
                        nextEpisodeEntity.getStill_path()
                ))
                .collect(Collectors.toList());
    }
}
