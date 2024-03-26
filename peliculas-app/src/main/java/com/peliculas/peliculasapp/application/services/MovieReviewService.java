package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.MovieReviewUseCase;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.dto.MovieReviewDTO;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MovieReviewService {
    private final MovieReviewUseCase movieReviewUseCase;

    public MovieReviewService(MovieReviewUseCase movieReviewUseCase) {
        this.movieReviewUseCase = movieReviewUseCase;
    }

    public Optional<MovieReviewDTO> createMovieReview(MovieReview movieReview) {
        return movieReviewUseCase.createMovieReview(movieReview);
    }
}
