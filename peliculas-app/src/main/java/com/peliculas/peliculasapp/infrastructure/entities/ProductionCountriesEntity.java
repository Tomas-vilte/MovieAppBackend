    package com.peliculas.peliculasapp.infrastructure.entities;
    import com.peliculas.peliculasapp.domain.models.ProductionCountries;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;

    @Entity
    public class ProductionCountriesEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;

        private String iso31661;

    public static ProductionCountriesEntity fromDomainModel(ProductionCountries productionCountries) {
        return new ProductionCountriesEntity();
    }

    public ProductionCountries toDomainModel() {
        return new ProductionCountries();
    }

}
