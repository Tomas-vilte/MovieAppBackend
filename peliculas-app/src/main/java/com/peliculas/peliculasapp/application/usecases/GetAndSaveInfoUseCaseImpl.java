package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.domain.ports.in.GetAndSaveInfoUseCase;
import com.peliculas.peliculasapp.domain.ports.out.MovieRepository;
import com.peliculas.peliculasapp.domain.ports.out.TvSeriesRepository;
import com.peliculas.peliculasapp.infrastructure.adapters.MovieDetailsAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAndSaveInfoUseCaseImpl implements GetAndSaveInfoUseCase {
    private final MovieDetailsAdapter movieDetailsAdapter;
    private final MovieRepository movieRepository;
    private final TvSeriesRepository tvSeriesRepository;

    @Autowired
    public GetAndSaveInfoUseCaseImpl(
            MovieDetailsAdapter movieDetailsAdapter, MovieRepository movieRepository, TvSeriesRepository tvSeriesRepository
    ) {
        this.movieDetailsAdapter = movieDetailsAdapter;
        this.movieRepository = movieRepository;
        this.tvSeriesRepository = tvSeriesRepository;
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
