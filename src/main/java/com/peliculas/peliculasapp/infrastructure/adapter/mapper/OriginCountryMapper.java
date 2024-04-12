package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.OriginCountryEntity;
import org.springframework.stereotype.Component;

@Component
public class OriginCountryMapper implements DomainEntityMapper<String, OriginCountryEntity> {

    @Override
    public OriginCountryEntity fromDomainModel(String country) {
        return new OriginCountryEntity(country);
    }

    @Override
    public String toDomainModel(OriginCountryEntity originCountryEntity) {
        return originCountryEntity.getName();
    }
}
