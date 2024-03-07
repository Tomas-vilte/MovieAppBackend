package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveMovieInfoUseCase;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.dto.MovieDTO;
import com.peliculas.peliculasapp.infrastructure.adapters.MovieDetailsAdapter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class GetAndSaveMovieInfoUseCaseImpl implements GetAndSaveMovieInfoUseCase {
    private final MovieDetailsAdapter movieDetailsAdapter;
    private final MovieRepositoryPort movieRepository;
    private final ModelMapper modelMapper;

    public GetAndSaveMovieInfoUseCaseImpl(
            MovieDetailsAdapter movieDetailsAdapter, MovieRepositoryPort movieRepository, ModelMapper modelMapper
    ) {
        this.movieDetailsAdapter = movieDetailsAdapter;
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MovieDTO getAndSaveMovieInfo(long movieId) {
        Movie movie = movieDetailsAdapter.getMovieInfoById(movieId);
        movieRepository.saveMovie(movie);
        return modelMapper.map(movie, MovieDTO.class);
    }

}
