package com.peliculas.peliculasapp.domain.ports.out;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import java.util.Optional;

public interface TvSeriesRepository {
    void saveTvSeriesInfo(TvSeries tvSeries);
    Optional<TvSeries> getTvSeriesById(long seriesId);
}
