package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.MovieReviewUseCase;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieReviewService {
    private final MovieReviewUseCase movieReviewUseCase;

    @Autowired
    public MovieReviewService(MovieReviewUseCase movieReviewUseCase) {
        this.movieReviewUseCase = movieReviewUseCase;
    }

    public Optional<MovieReviewDTO> createMovieReview(MovieReview movieReview) {
        return movieReviewUseCase.createMovieReview(movieReview);
    }

    public List<MovieReviewDTO> findReviewsByMovieId(long reviewId) {
        return movieReviewUseCase.findReviewsByMovieId(reviewId);
    }
}
