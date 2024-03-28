package com.peliculas.peliculasapp.infrastructure.mapper;
import com.peliculas.peliculasapp.domain.models.LastEpisode;
import com.peliculas.peliculasapp.infrastructure.entities.LastEpisodeEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LastEpisodeEntityMapper implements ListMapper<LastEpisode, LastEpisodeEntity>{

    @Override
    public List<LastEpisodeEntity> fromDomainModel(List<LastEpisode> lastEpisodeList) {
        return lastEpisodeList.stream()
                .map(lastEpisode -> new LastEpisodeEntity(
                        lastEpisode.getName(),
                        lastEpisode.getOverview(),
                        lastEpisode.getVote_count(),
                        lastEpisode.getAir_date(),
                        lastEpisode.getEpisode_number(),
                        lastEpisode.getEpisode_type(),
                        lastEpisode.getProduction_code(),
                        lastEpisode.getRuntime(),
                        lastEpisode.getSeason_number(),
                        lastEpisode.getShow_id(),
                        lastEpisode.getStill_path()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<LastEpisode> toDomainModel(List<LastEpisodeEntity> lastEpisodeEntityList) {
        return lastEpisodeEntityList.stream()
                .map(lastEpisodeEntity -> new LastEpisode(
                        lastEpisodeEntity.getId(),
                        lastEpisodeEntity.getName(),
                        lastEpisodeEntity.getOverview(),
                        lastEpisodeEntity.getVote_count(),
                        lastEpisodeEntity.getAir_date(),
                        lastEpisodeEntity.getEpisode_number(),
                        lastEpisodeEntity.getEpisode_type(),
                        lastEpisodeEntity.getProduction_code(),
                        lastEpisodeEntity.getRuntime(),
                        lastEpisodeEntity.getSeason_number(),
                        lastEpisodeEntity.getShow_id(),
                        lastEpisodeEntity.getStill_path()
                ))
                .collect(Collectors.toList());
    }
}
