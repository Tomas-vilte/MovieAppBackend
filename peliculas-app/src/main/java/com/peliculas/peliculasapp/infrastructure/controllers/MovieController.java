package com.peliculas.peliculasapp.infrastructure.controllers;
import com.peliculas.peliculasapp.application.services.MovieService;
import com.peliculas.peliculasapp.infrastructure.common.ErrorResponse;
import com.peliculas.peliculasapp.infrastructure.common.SuccessResponse;
import com.peliculas.peliculasapp.infrastructure.exceptions.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAndSaveMovieInfo(@PathVariable long id) {
        try {
            movieService.saveMovieInfo(id);
            SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Pelicula guardada con exito en la base de datos");
            return ResponseEntity.ok(successResponse);
        } catch (MovieNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Pelicula no encontrada con el ID: " + id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
