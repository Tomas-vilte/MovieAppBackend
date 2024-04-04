package com.peliculas.peliculasapp.infrastructure.rest.controllers;
import com.peliculas.peliculasapp.application.services.MovieReviewService;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import com.peliculas.peliculasapp.infrastructure.common.SuccessResponse;
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
    public ResponseEntity<?> createMovieReview(@RequestBody MovieReview movieReview) {
        Optional<MovieReviewDTO> createdReview = movieReviewService.createMovieReview(movieReview);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Review de pelicula creada con exito", createdReview);
        return ResponseEntity.ok(successResponse);
    }
}
