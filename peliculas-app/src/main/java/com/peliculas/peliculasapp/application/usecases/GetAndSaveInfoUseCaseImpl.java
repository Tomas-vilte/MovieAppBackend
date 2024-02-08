package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveInfoUseCase;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesRepository;
import com.peliculas.peliculasapp.infrastructure.adapters.MovieDetailsAdapter;
import org.springframework.stereotype.Service;


@Service
public class GetAndSaveInfoUseCaseImpl implements GetAndSaveInfoUseCase {
    private final MovieDetailsAdapter movieDetailsAdapter;
    private final MovieRepositoryPort movieRepository;
    private final TvSeriesRepository tvSeriesRepository;

    public GetAndSaveInfoUseCaseImpl(
            MovieDetailsAdapter movieDetailsAdapter, MovieRepositoryPort movieRepository, TvSeriesRepository tvSeriesRepository
    ) {
        this.movieDetailsAdapter = movieDetailsAdapter;
        this.tvSeriesRepository = tvSeriesRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void getAndSaveMovieInfo(long movieId) {
        Movie movie = movieDetailsAdapter.getMovieInfoById(movieId);
        movieRepository.saveMovieInfo(movie);
    }

    @Override
    public void getAndSaveTvSeriesInfo(long tvSeriesId) {
        TvSeries tvSeries = movieDetailsAdapter.getTvSeriesInfoById(tvSeriesId);

        tvSeriesRepository.saveTvSeriesInfo(tvSeries);
    }
}
