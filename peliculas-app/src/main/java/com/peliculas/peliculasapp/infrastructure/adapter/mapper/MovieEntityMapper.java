package com.peliculas.peliculasapp.infrastructure.adapter.mapper;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.GenreEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.ProductionCompaniesEntity;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.ProductionCountriesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MovieEntityMapper implements DomainEntityMapper<Movie, MovieEntity>{
    private final ProductionCountriesEntityMapper productionCountriesEntityMapper;
    private final ProductionCompaniesEntityMapper productionCompaniesEntityMapper;
    private final GenreEntityMapper genreEntityMapper;

    @Autowired
    public MovieEntityMapper(ProductionCountriesEntityMapper productionCountriesEntityMapper, ProductionCompaniesEntityMapper productionCompaniesEntityMapper, GenreEntityMapper genreEntityMapper) {
        this.productionCountriesEntityMapper = productionCountriesEntityMapper;
        this.productionCompaniesEntityMapper = productionCompaniesEntityMapper;
        this.genreEntityMapper = genreEntityMapper;
    }

    public MovieEntity fromDomainModel(Movie movie) {
        List<ProductionCountriesEntity> productionCountriesEntities = productionCountriesEntityMapper.fromDomainModel(movie.getProduction_countries());
        List<ProductionCompaniesEntity> productionCompaniesEntities = productionCompaniesEntityMapper.fromDomainModel(movie.getProduction_companies());
        List<GenreEntity> genreEntities = genreEntityMapper.fromDomainModel(movie.getGenres());

        return new MovieEntity(
                movie.getId(),
                movie.getOverview(),
                movie.getStatus(),
                productionCompaniesEntities,
                genreEntities,
                productionCountriesEntities,
                movie.getTitle(),
                movie.getVote_average(),
                movie.getVote_count(),
                movie.getRevenue(),
                movie.getBudget(),
                movie.getPopularity(),
                movie.getPoster_path(),
                movie.getRelease_date()
        );
    }

    public Movie toDomainModel(MovieEntity movieEntity) {
        List<ProductionCountries> productionCountries = productionCountriesEntityMapper.toDomainModel(movieEntity.getProductionCountries());
        List<ProductionCompany> productionCompanies = productionCompaniesEntityMapper.toDomainModel(movieEntity.getProductionCompanies());
        List<Genre> genres = genreEntityMapper.toDomainModel(movieEntity.getGenres());


        return new Movie(
                movieEntity.getId(),
                movieEntity.getOverview(),
                movieEntity.getStatus(),
                productionCompanies,
                genres,
                productionCountries,
                movieEntity.getTitle(),
                movieEntity.getVoteAverage(),
                movieEntity.getVoteCount(),
                movieEntity.getRevenue(),
                movieEntity.getBudget(),
                movieEntity.getPopularity(),
                movieEntity.getPosterPath(),
                movieEntity.getReleaseDate()
        );
    }
}
