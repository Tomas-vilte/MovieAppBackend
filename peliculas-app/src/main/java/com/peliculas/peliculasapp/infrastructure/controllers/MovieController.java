package com.peliculas.peliculasapp.infrastructure.controllers;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveInfoUseCase;
import com.peliculas.peliculasapp.application.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/movies")
public class MovieController {
    private MovieService movieService;

    public MovieController() {}

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public void getAndSaveMovieInfo(@PathVariable long id) {
        movieService.saveMovieInfo(id);
    }
}
