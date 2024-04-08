package com.peliculas.peliculasapp.infrastructure.rest.controllers;
import com.peliculas.peliculasapp.application.services.MovieReviewService;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.domain.dto.MovieReviewDTO;
import com.peliculas.peliculasapp.infrastructure.common.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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

    @GetMapping("/movies/review/{reviewId}")
    public ResponseEntity<?> getMovieReviewById(@PathVariable long reviewId) {
        List<MovieReviewDTO> movieReviewDTO = movieReviewService.findReviewsByMovieId(reviewId);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Review obtenida con exito", movieReviewDTO);
        return ResponseEntity.ok(successResponse);
    }

    @DeleteMapping("/movies/review/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable long reviewId) {
        movieReviewService.deleteReviewById(reviewId);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Review eliminada con exito", "Se elimino la review");
        return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/movies/review/{reviewId}")
    public ResponseEntity<?> updateMovieReview(@PathVariable long reviewId, @RequestBody MovieReview review) {
        review.setId(reviewId);
        Optional<MovieReviewDTO> movieReviewDTO = movieReviewService.updateMovieReview(review);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Review actualizada con exito", movieReviewDTO);
        return ResponseEntity.ok(successResponse);
    }
}
