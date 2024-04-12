package com.peliculas.peliculasapp.infrastructure.rest.controllers;
import com.peliculas.peliculasapp.application.services.MovieService;
import com.peliculas.peliculasapp.domain.dto.MovieDTO;
import com.peliculas.peliculasapp.domain.dto.MovieInfoDTO;
import com.peliculas.peliculasapp.infrastructure.common.SuccessResponse;
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
        MovieDTO movie = movieService.saveMovieInfo(id);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Pelicula guardada con exito en la base de datos", movie);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieInfoById(@PathVariable long movieId) {
        MovieInfoDTO movieInfoDTO = movieService.getMovieById(movieId);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Pelicula obtenida con exito", movieInfoDTO);
        return ResponseEntity.ok(successResponse);
    }
}
