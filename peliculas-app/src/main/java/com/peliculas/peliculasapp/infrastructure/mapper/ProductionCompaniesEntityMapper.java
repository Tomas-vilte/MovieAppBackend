package com.peliculas.peliculasapp.infrastructure.mapper;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.infrastructure.entities.ProductionCompaniesEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductionCompaniesEntityMapper {
    public List<ProductionCompaniesEntity> fromDomainModel(List<ProductionCompany> companies) {
        return companies.stream()
                .map(productionCompany -> new ProductionCompaniesEntity(productionCompany.getName(), productionCompany.getLogo_path(), productionCompany.getOrigin_country()))
                .collect(Collectors.toList());
    }

    public List<ProductionCompany> toDomainModel(List<ProductionCompaniesEntity> companiesEntityList) {
        return companiesEntityList.stream()
                .map(productionCompany -> new ProductionCompany(productionCompany.getName(), productionCompany.getLogoPath(), productionCompany.getOriginCountry()))
                .collect(Collectors.toList());
    }
}
