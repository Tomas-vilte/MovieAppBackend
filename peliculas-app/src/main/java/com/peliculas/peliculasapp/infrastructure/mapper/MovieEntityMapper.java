package com.peliculas.peliculasapp.infrastructure.mapper;
import com.peliculas.peliculasapp.application.ports.incoming.EntityMapper;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import com.peliculas.peliculasapp.infrastructure.entities.GenreEntity;
import com.peliculas.peliculasapp.infrastructure.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.entities.ProductionCompaniesEntity;
import com.peliculas.peliculasapp.infrastructure.entities.ProductionCountriesEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MovieEntityMapper implements EntityMapper<MovieEntity, Movie> {

    @Override
    public Movie toDomainModel(MovieEntity entity) {
        List<ProductionCountries> productionCountries = entity.getProductionCountries().stream()
                .map(ProductionCountriesEntity::toDomainModel)
                .toList();

        List<ProductionCompany> productionCompanies = entity.getProductionCompanies().stream()
                .map(ProductionCompaniesEntity::toDomainModel)
                .toList();

        List<Genre> genres = entity.getGenres().stream()
                .map(GenreEntity::toDomainModel)
                .toList();
        return new Movie(
                entity.getMovieId(),
                entity.getOverview(),
                entity.getStatus(),
                productionCompanies,
                genres,
                productionCountries,
                entity.getTitle(),
                entity.getVoteAverage(),
                entity.getVoteCount(),
                entity.getRevenue(),
                entity.getBudget(),
                entity.getPopularity(),
                entity.getPosterPath(),
                entity.getReleaseDate()
        );
    }

    @Override
    public MovieEntity toEntity(Movie model) {
        List<ProductionCountriesEntity> productionCountriesEntities = model.getProduction_countries().stream()
                .map(ProductionCountriesEntity::fromDomainModel)
                .toList();

        List<ProductionCompaniesEntity> productionCompanyEntities = model.getProduction_companies().stream()
                .map(ProductionCompaniesEntity::fromDomainModel)
                .toList();

        List<GenreEntity> genreEntities = model.getGenres().stream()
                .map(GenreEntity::fromDomainModel)
                .toList();

        return new MovieEntity(
                model.getId(),
                model.getOverview(),
                model.getStatus(),
                productionCompanyEntities,
                genreEntities,
                productionCountriesEntities,
                model.getTitle(),
                model.getVote_average(),
                model.getVote_count(),
                model.getRevenue(),
                model.getBudget(),
                model.getPopularity(),
                model.getPoster_path(),
                model.getRelease_date()
        );
    }
}
