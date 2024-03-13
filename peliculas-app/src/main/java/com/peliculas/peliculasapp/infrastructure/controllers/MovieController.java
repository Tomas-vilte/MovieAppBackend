package com.peliculas.peliculasapp.infrastructure.controllers;
import com.peliculas.peliculasapp.application.services.MovieService;
import com.peliculas.peliculasapp.dto.MovieDTO;
import com.peliculas.peliculasapp.dto.MovieInfoDTO;
import com.peliculas.peliculasapp.infrastructure.common.ErrorResponse;
import com.peliculas.peliculasapp.infrastructure.common.SuccessResponse;
import com.peliculas.peliculasapp.infrastructure.exceptions.MovieAlreadyExistsException;
import com.peliculas.peliculasapp.infrastructure.exceptions.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> getAndSaveMovieInfo(@PathVariable long id) {
        try {
            MovieDTO movie = movieService.saveMovieInfo(id);
            SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Pelicula guardada con exito en la base de datos", movie);
            return ResponseEntity.ok(successResponse);
        } catch (MovieAlreadyExistsException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(),"Pelicula ya se encuentra en la base de datos", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (MovieNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Pelicula no encontrada con el ID: " + id + ", en el servicio externo", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieInfoById(@PathVariable long movieId) {
        try {
            MovieInfoDTO movieInfoDTO = movieService.getMovieById(movieId);
            SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Pelicula obtenida con exito", movieInfoDTO);
            return ResponseEntity.ok(successResponse);
        } catch (MovieNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Pelicula no encontrada con el ID: " + movieId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
