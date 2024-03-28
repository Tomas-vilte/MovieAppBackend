package com.peliculas.peliculasapp.infrastructure.mapper;
import com.peliculas.peliculasapp.domain.models.*;
import com.peliculas.peliculasapp.infrastructure.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TvSeriesEntityMapper {
    private final CreatedSeriesEntityMapper createdSeriesEntityMapper;
    private final GenreEntityMapper genreEntityMapper;
    private final LastEpisodeEntityMapper lastEpisodeEntityMapper;
    private final NextEpisodeEntityMapper nextEpisodeEntityMapper;
    private final NetworksEntityMapper networksEntityMapper;
    private final OriginCountryMapper originCountryMapper;
    private final ProductionCountriesEntityMapper productionCountriesEntityMapper;
    private final ProductionCompaniesEntityMapper productionCompaniesEntityMapper;
    private final SeasonsEntityMapper seasonsEntityMapper;
    private final SpokenLanguagesEntityMapper spokenLanguagesEntityMapper;

    @Autowired
    public TvSeriesEntityMapper(CreatedSeriesEntityMapper createdSeriesEntityMapper,
                                GenreEntityMapper genreEntityMapper,
                                LastEpisodeEntityMapper lastEpisodeEntityMapper,
                                NextEpisodeEntityMapper nextEpisodeEntityMapper,
                                NetworksEntityMapper networksEntityMapper,
                                OriginCountryMapper originCountryMapper,
                                ProductionCountriesEntityMapper productionCountriesEntityMapper,
                                ProductionCompaniesEntityMapper productionCompaniesEntityMapper,
                                SeasonsEntityMapper seasonsEntityMapper,
                                SpokenLanguagesEntityMapper spokenLanguagesEntityMapper)
    {
        this.createdSeriesEntityMapper = createdSeriesEntityMapper;
        this.genreEntityMapper = genreEntityMapper;
        this.lastEpisodeEntityMapper = lastEpisodeEntityMapper;
        this.nextEpisodeEntityMapper = nextEpisodeEntityMapper;
        this.networksEntityMapper = networksEntityMapper;
        this.originCountryMapper = originCountryMapper;
        this.productionCountriesEntityMapper = productionCountriesEntityMapper;
        this.productionCompaniesEntityMapper = productionCompaniesEntityMapper;
        this.seasonsEntityMapper = seasonsEntityMapper;
        this.spokenLanguagesEntityMapper = spokenLanguagesEntityMapper;
    }

    public TvSeriesEntity fromDomainModel(TvSeries tvSeries) {
        List<CreatedSeriesEntity> createdSeriesEntityList = createdSeriesEntityMapper.fromDomainModel(tvSeries.getCreated_by());
        List<GenreEntity>  genreEntityList = genreEntityMapper.fromDomainModel(tvSeries.getGenres());
        LastEpisodeEntity lastEpisodeEntity = lastEpisodeEntityMapper.fromDomainModel(tvSeries.getLast_episode_to_air());
        NextEpisodeEntity nextEpisodeEntity = nextEpisodeEntityMapper.fromDomainModel(tvSeries.getNext_episode_to_air());
        List<NetworksEntity> networksEntity = networksEntityMapper.fromDomainModel(tvSeries.getNetworks());
        List<OriginCountryEntity> originCountryEntities = tvSeries.getOrigin_country().stream()
                .map(originCountryMapper::fromDomainModel)
                .collect(Collectors.toList());
        List<ProductionCountriesEntity> productionCountriesEntities = productionCountriesEntityMapper.fromDomainModel(tvSeries.getProduction_countries());
        List<ProductionCompaniesEntity> productionCompaniesEntities = productionCompaniesEntityMapper.fromDomainModel(tvSeries.getProduction_companies());
        List<SeasonsEntity> seasonsEntities = seasonsEntityMapper.fromDomainModel(tvSeries.getSeasons());
        List<SpokenLanguagesEntity> spokenLanguagesEntities = spokenLanguagesEntityMapper.fromDomainModel(tvSeries.getSpoken_languages());


        return new TvSeriesEntity(
                tvSeries.getId(),
                tvSeries.getBackdrop_path(),
                createdSeriesEntityList,
                tvSeries.getFirst_air_date(),
                genreEntityList,
                tvSeries.getHomepage(),
                tvSeries.isIn_production(),
                tvSeries.getLast_air_date(),
                lastEpisodeEntity,
                tvSeries.getName(),
                nextEpisodeEntity,
                networksEntity,
                tvSeries.getNumber_of_episodes(),
                tvSeries.getNumber_of_seasons(),
                originCountryEntities,
                tvSeries.getOriginal_language(),
                tvSeries.getOriginal_name(),
                tvSeries.getOverview(),
                tvSeries.getPopularity(),
                tvSeries.getPoster_path(),
                productionCompaniesEntities,
                productionCountriesEntities,
                seasonsEntities,
                spokenLanguagesEntities,
                tvSeries.getStatus(),
                tvSeries.getTagline(),
                tvSeries.getVote_average(),
                tvSeries.getVote_count()
        );
    }

    public TvSeries toDomainModel(TvSeriesEntity tvSeriesEntity) {
        List<CreatedSeries> createdSeriesList = createdSeriesEntityMapper.toDomainModel(tvSeriesEntity.getCreatedBy());
        List<Genre> genreList = genreEntityMapper.toDomainModel(tvSeriesEntity.getGenres());
        LastEpisode lastEpisode = lastEpisodeEntityMapper.toDomainModel(tvSeriesEntity.getLastEpisodeToAir());
        NextEpisode nextEpisode = nextEpisodeEntityMapper.toDomainModel(tvSeriesEntity.getNextEpisodeToAir());
        List<Networks> networksList = networksEntityMapper.toDomainModel(tvSeriesEntity.getNetworks());
        List<String> originCountryList = tvSeriesEntity.getOriginCountry().stream()
                .map(originCountryMapper::toDomainModel)
                .collect(Collectors.toList());
        List<ProductionCountries> productionCountriesList = productionCountriesEntityMapper.toDomainModel(tvSeriesEntity.getProductionCountries());
        List<ProductionCompany> productionCompaniesList = productionCompaniesEntityMapper.toDomainModel(tvSeriesEntity.getProductionCompanies());
        List<Seasons> seasonsList = seasonsEntityMapper.toDomainModel(tvSeriesEntity.getSeasons());
        List<SpokenLanguages> spokenLanguagesList = spokenLanguagesEntityMapper.toDomainModel(tvSeriesEntity.getSpokenLanguages());

        return new TvSeries(
                tvSeriesEntity.getId(),
                tvSeriesEntity.getBackdropPath(),
                createdSeriesList,
                tvSeriesEntity.getFirstAirDate(),
                genreList,
                tvSeriesEntity.getHomePage(),
                tvSeriesEntity.isInProduction(),
                tvSeriesEntity.getLastAirDate(),
                lastEpisode,
                tvSeriesEntity.getName(),
                nextEpisode,
                networksList,
                tvSeriesEntity.getNumberOfEpisodes(),
                tvSeriesEntity.getNumberOfSeasons(),
                originCountryList,
                tvSeriesEntity.getOriginalLanguage(),
                tvSeriesEntity.getOriginalName(),
                tvSeriesEntity.getOverview(),
                tvSeriesEntity.getPopularity(),
                tvSeriesEntity.getPosterPath(),
                productionCompaniesList,
                productionCountriesList,
                seasonsList,
                spokenLanguagesList,
                tvSeriesEntity.getStatus(),
                tvSeriesEntity.getTagline(),
                tvSeriesEntity.getVoteAverage(),
                tvSeriesEntity.getVoteCount()
        );
    }
}
