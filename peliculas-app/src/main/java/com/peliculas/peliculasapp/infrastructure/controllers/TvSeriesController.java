package com.peliculas.peliculasapp.infrastructure.controllers;
import com.peliculas.peliculasapp.domain.ports.in.GetAndSaveInfoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tvseries")
public class TvSeriesController {
    private final GetAndSaveInfoUseCase getAndSaveInfoUseCase;

    @Autowired
    public TvSeriesController(GetAndSaveInfoUseCase getAndSaveInfoUseCase) {
        this.getAndSaveInfoUseCase = getAndSaveInfoUseCase;
    }

    @GetMapping("/{id}")
    public void getAndSaveTvSeriesInfo(@PathVariable long id) {
        getAndSaveInfoUseCase.getAndSaveTvSeriesInfo(id);
    }
}
