package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import com.peliculas.peliculasapp.domain.models.SpokenLanguages;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.SpokenLanguagesEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpokenLanguagesEntityMapper implements ListMapper<SpokenLanguages, SpokenLanguagesEntity>{
    @Override
    public List<SpokenLanguagesEntity> fromDomainModel(List<SpokenLanguages> spokenLanguagesList) {
        return spokenLanguagesList.stream()
                .map(spokenLanguages -> new SpokenLanguagesEntity(
                        spokenLanguages.getEnglish_name(),
                        spokenLanguages.getIso_639_1(),
                        spokenLanguages.getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<SpokenLanguages> toDomainModel(List<SpokenLanguagesEntity> spokenLanguagesEntityList) {
        return spokenLanguagesEntityList.stream()
                .map(spokenLanguagesEntity -> new SpokenLanguages(
                        spokenLanguagesEntity.getEnglish_name(),
                        spokenLanguagesEntity.getIso_639_1(),
                        spokenLanguagesEntity.getName()
                ))
                .collect(Collectors.toList());
    }
}
