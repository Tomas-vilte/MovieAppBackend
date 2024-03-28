package com.peliculas.peliculasapp.infrastructure.mapper;
import com.peliculas.peliculasapp.domain.models.LastEpisode;
import com.peliculas.peliculasapp.infrastructure.entities.LastEpisodeEntity;
import org.springframework.stereotype.Component;

@Component
public class LastEpisodeEntityMapper implements DomainEntityMapper<LastEpisode, LastEpisodeEntity>{
    @Override
    public LastEpisodeEntity fromDomainModel(LastEpisode lastEpisodeList) {
        return new LastEpisodeEntity(
                lastEpisodeList.getName(),
                lastEpisodeList.getOverview(),
                lastEpisodeList.getVote_count(),
                lastEpisodeList.getAir_date(),
                lastEpisodeList.getEpisode_number(),
                lastEpisodeList.getEpisode_type(),
                lastEpisodeList.getProduction_code(),
                lastEpisodeList.getRuntime(),
                lastEpisodeList.getSeason_number(),
                lastEpisodeList.getShow_id(),
                lastEpisodeList.getStill_path()
        );
    }

    @Override
    public LastEpisode toDomainModel(LastEpisodeEntity lastEpisodeEntity) {
       return new LastEpisode(
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
       );
    }
}
