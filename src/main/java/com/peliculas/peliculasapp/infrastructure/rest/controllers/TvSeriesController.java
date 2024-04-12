package com.peliculas.peliculasapp.infrastructure.rest.controllers;
import com.peliculas.peliculasapp.application.services.TvSeriesService;
import com.peliculas.peliculasapp.domain.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.domain.dto.TvSeriesInfoDTO;
import com.peliculas.peliculasapp.infrastructure.common.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/series")
public class TvSeriesController {
    private final TvSeriesService tvSeriesService;

    @Autowired
    public TvSeriesController(TvSeriesService tvSeriesService) {
        this.tvSeriesService = tvSeriesService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> getAndSaveTvSeriesInfo(@PathVariable long id) {
        TvSeriesDTO seriesDTO = tvSeriesService.saveTvSeriesInfo(id);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Serie guardada con exito en la base de datos.", seriesDTO);
        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/{tvSeriesId}")
    public ResponseEntity<?> getTvSeriesById(@PathVariable long tvSeriesId) {
        TvSeriesInfoDTO seriesInfoDTO = tvSeriesService.getTvSeriesInfoById(tvSeriesId);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Serie obtenida con exito", seriesInfoDTO);
        return ResponseEntity.ok(successResponse);
    }
}
