package com.peliculas.peliculasapp.infrastructure.mapper;
import com.peliculas.peliculasapp.domain.models.Seasons;
import com.peliculas.peliculasapp.infrastructure.entities.SeasonsEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeasonsEntityMapper implements ListMapper<Seasons, SeasonsEntity>{
    @Override
    public List<SeasonsEntity> fromDomainModel(List<Seasons> seasonsList) {
        return seasonsList.stream()
                .map(seasons -> new SeasonsEntity(
                        seasons.getAir_date(),
                        seasons.getEpisode_count(),
                        seasons.getName(),
                        seasons.getOverview(),
                        seasons.getPoster_path(),
                        seasons.getSeason_number(),
                        seasons.getVote_average()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Seasons> toDomainModel(List<SeasonsEntity> seasonsEntityList) {
        return seasonsEntityList.stream()
                .map(seasonsEntity -> new Seasons(
                        seasonsEntity.getAirDate(),
                        seasonsEntity.getEpisodeCount(),
                        seasonsEntity.getName(),
                        seasonsEntity.getOverview(),
                        seasonsEntity.getPosterPath(),
                        seasonsEntity.getSeasonNumber(),
                        seasonsEntity.getVoteAverage()
                ))
                .collect(Collectors.toList());
    }
}
