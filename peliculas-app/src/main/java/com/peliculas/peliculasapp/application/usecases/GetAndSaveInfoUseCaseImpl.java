package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.TvSeries;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveInfoUseCase;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.application.ports.out.TvSeriesRepository;
import com.peliculas.peliculasapp.dto.MovieDTO;
import com.peliculas.peliculasapp.infrastructure.adapters.MovieDetailsAdapter;
import com.peliculas.peliculasapp.infrastructure.adapters.SeriesDetailsAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class GetAndSaveInfoUseCaseImpl implements GetAndSaveInfoUseCase {
    private final MovieDetailsAdapter movieDetailsAdapter;
    private final SeriesDetailsAdapter seriesDetailsAdapter;
    private final MovieRepositoryPort movieRepository;
    private final TvSeriesRepository tvSeriesRepository;
    private final ModelMapper modelMapper;

    public GetAndSaveInfoUseCaseImpl(
            MovieDetailsAdapter movieDetailsAdapter, MovieRepositoryPort movieRepository, TvSeriesRepository tvSeriesRepository,
            SeriesDetailsAdapter seriesDetailsAdapter,
            ModelMapper modelMapper
    ) {
        this.movieDetailsAdapter = movieDetailsAdapter;
        this.tvSeriesRepository = tvSeriesRepository;
        this.movieRepository = movieRepository;
        this.seriesDetailsAdapter = seriesDetailsAdapter;
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieDTO getAndSaveMovieInfo(long movieId) {
        Movie movie = movieDetailsAdapter.getMovieInfoById(movieId);
        movieRepository.saveMovie(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public void getAndSaveTvSeriesInfo(long tvSeriesId) {
        TvSeries tvSeries = seriesDetailsAdapter.getTvSeriesInfoById(tvSeriesId);
        tvSeriesRepository.saveTvSeriesInfo(tvSeries);
    }
}
