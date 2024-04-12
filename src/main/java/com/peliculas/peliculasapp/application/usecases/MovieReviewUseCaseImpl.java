package com.peliculas.peliculasapp.application.usecases;
import com.peliculas.peliculasapp.application.mapper.MovieReviewDtoMapper;
import com.peliculas.peliculasapp.application.ports.in.MovieReviewUseCase;
import com.peliculas.peliculasapp.application.ports.out.MovieReviewRepositoryPort;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import com.peliculas.peliculasapp.infrastructure.adapter.exceptions.MovieReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieReviewUseCaseImpl implements MovieReviewUseCase {
    private final MovieReviewDtoMapper movieReviewMapper;
    private final MovieReviewRepositoryPort movieReviewRepositoryPort;
    @Autowired
    public MovieReviewUseCaseImpl(MovieReviewDtoMapper movieReviewMapper, MovieReviewRepositoryPort movieReviewRepositoryPort) {
        this.movieReviewMapper = movieReviewMapper;
        this.movieReviewRepositoryPort = movieReviewRepositoryPort;
    }
    @Override
    public Optional<MovieReviewDTO> createMovieReview(MovieReview movieReview) {
        Optional<MovieReview> movieReviewOptional = movieReviewRepositoryPort.createMovieReview(movieReview);
        return movieReviewOptional.map(movieReviewMapper::toDto);
    }

    @Override
    public Optional<MovieReviewDTO> updateMovieReview(MovieReview review) {
        Optional<MovieReview> movieReview = movieReviewRepositoryPort.updateMovieReview(review);
        return movieReview.map(movieReviewMapper::toDto);
    }

    @Override
    public List<MovieReviewDTO> findAllReviewsByMovieId(long reviewId) {
        List<MovieReview> movieReview = movieReviewRepositoryPort.findAllReviewsByMovieId(reviewId);
        return movieReview.stream()
                .map(movieReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReviewById(long reviewId) {
        Optional<MovieReview> optionalMovieReview = movieReviewRepositoryPort.getMovieReviewById(reviewId);
        optionalMovieReview.ifPresentOrElse(
                movieReview -> {
                    movieReviewRepositoryPort.deleteReviewById(movieReview.getId());
                },
                () -> {
                    throw new MovieReviewNotFoundException("No se encontró ninguna reseña de película con el ID: " + reviewId);
                }
        );
    }
}
