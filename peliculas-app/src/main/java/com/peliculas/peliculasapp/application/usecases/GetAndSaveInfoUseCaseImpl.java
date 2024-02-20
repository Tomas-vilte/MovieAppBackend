package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveInfoUseCase;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesRepository;
import com.peliculas.peliculasapp.infrastructure.adapters.MovieDetailsAdapter;
import com.peliculas.peliculasapp.infrastructure.adapters.SeriesDetailsAdapter;
import org.springframework.stereotype.Service;


@Service
public class GetAndSaveInfoUseCaseImpl implements GetAndSaveInfoUseCase {
    private final MovieDetailsAdapter movieDetailsAdapter;
    private final SeriesDetailsAdapter seriesDetailsAdapter;
    private final MovieRepositoryPort movieRepository;
    private final TvSeriesRepository tvSeriesRepository;

    public GetAndSaveInfoUseCaseImpl(
            MovieDetailsAdapter movieDetailsAdapter, MovieRepositoryPort movieRepository, TvSeriesRepository tvSeriesRepository,
            SeriesDetailsAdapter seriesDetailsAdapter
    ) {
        this.movieDetailsAdapter = movieDetailsAdapter;
        this.tvSeriesRepository = tvSeriesRepository;
        this.movieRepository = movieRepository;
        this.seriesDetailsAdapter = seriesDetailsAdapter;
    }

    @Override
    public void getAndSaveMovieInfo(long movieId) {
        Movie movie = movieDetailsAdapter.getMovieInfoById(movieId);
        movieRepository.findById(movie);
    }

    @Override
    public void getAndSaveTvSeriesInfo(long tvSeriesId) {
        TvSeries tvSeries = seriesDetailsAdapter.getTvSeriesInfoById(tvSeriesId);
        tvSeriesRepository.saveTvSeriesInfo(tvSeries);
    }
}
