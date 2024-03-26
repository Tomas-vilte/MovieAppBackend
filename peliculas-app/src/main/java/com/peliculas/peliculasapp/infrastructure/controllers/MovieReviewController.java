package com.peliculas.peliculasapp.infrastructure.controllers;
import com.peliculas.peliculasapp.application.services.MovieReviewService;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.dto.MovieReviewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class MovieReviewController {

    private final MovieReviewService movieReviewService;

    public MovieReviewController(MovieReviewService movieReviewService) {
        this.movieReviewService = movieReviewService;
    }

    @PostMapping("/movies/create")
    public ResponseEntity<MovieReviewDTO> createMovieReview(@RequestBody MovieReview movieReview) {
        Optional<MovieReviewDTO> createdReview = movieReviewService.createMovieReview(movieReview);
        return createdReview.map(movieReviewDTO -> new ResponseEntity<>(movieReviewDTO, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
