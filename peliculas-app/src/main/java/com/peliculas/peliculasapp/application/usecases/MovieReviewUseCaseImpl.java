package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.ports.in.MovieReviewUseCase;
import com.peliculas.peliculasapp.application.ports.out.MovieReviewRepositoryPort;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MovieReviewUseCaseImpl implements MovieReviewUseCase {
    private final MovieReviewRepositoryPort movieReviewRepositoryPort;
    private final ModelMapper modelMapper;
    public MovieReviewUseCaseImpl(MovieReviewRepositoryPort movieReviewRepositoryPort, ModelMapper modelMapper) {
        this.movieReviewRepositoryPort = movieReviewRepositoryPort;
        this.modelMapper = modelMapper;
    }
    @Override
    public Optional<MovieReviewDTO> createMovieReview(MovieReview movieReview) {
        Optional<MovieReview> optionalMovieReview = movieReviewRepositoryPort.createMovieReview(movieReview);
        return Optional.of(modelMapper.map(optionalMovieReview, MovieReviewDTO.class));
    }

    @Override
    public Optional<MovieReview> updateMovieReview(MovieReview review) {
        return Optional.empty();
    }

    @Override
    public Optional<MovieReview> findReviewsByMovieId(long reviewId) {
        return Optional.empty();
    }

    @Override
    public Optional<MovieReview> deleteReviewById(long reviewId) {
        return Optional.empty();
    }
}
