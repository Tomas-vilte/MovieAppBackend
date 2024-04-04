package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.ProductionCountriesEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductionCountriesEntityMapper {

    public List<ProductionCountriesEntity> fromDomainModel(List<ProductionCountries> productionCountriesList) {
        return productionCountriesList.stream()
                .map(productionCountries -> new ProductionCountriesEntity(productionCountries.getName(), productionCountries.getIso_3166_1()))
                .collect(Collectors.toList());
    }

    public List<ProductionCountries> toDomainModel(List<ProductionCountriesEntity> productionCountriesEntityList) {
        return productionCountriesEntityList.stream()
                .map(productionCountriesEntity -> new ProductionCountries(productionCountriesEntity.getName(), productionCountriesEntity.getIso31661()))
                .collect(Collectors.toList());
    }

}
