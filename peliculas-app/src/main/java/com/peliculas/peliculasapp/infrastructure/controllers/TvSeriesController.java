package com.peliculas.peliculasapp.infrastructure.controllers;
import com.peliculas.peliculasapp.application.services.TvSeriesService;
import com.peliculas.peliculasapp.dto.TvSeriesDTO;
import com.peliculas.peliculasapp.dto.TvSeriesInfoDTO;
import com.peliculas.peliculasapp.infrastructure.common.ErrorResponse;
import com.peliculas.peliculasapp.infrastructure.common.SuccessResponse;
import com.peliculas.peliculasapp.infrastructure.exceptions.TvSeriesAlreadyExistsException;
import com.peliculas.peliculasapp.infrastructure.exceptions.TvSeriesNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/series")
public class TvSeriesController {
    private final TvSeriesService tvSeriesService;

    public TvSeriesController(TvSeriesService tvSeriesService) {
        this.tvSeriesService = tvSeriesService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> getAndSaveTvSeriesInfo(@PathVariable long id) {
        try {
            TvSeriesDTO seriesInfo = tvSeriesService.saveTvSeriesInfo(id);
            SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Serie guardada con exito en la base de datos.", seriesInfo);
            return ResponseEntity.ok(successResponse);
        } catch (TvSeriesAlreadyExistsException exception) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), "Esta serie ya se encuentra en la base de datos", exception.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (TvSeriesNotFoundException seriesNotFoundException) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Esta serie no se encuentra en el servicio externo", seriesNotFoundException.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/{tvSeriesId}")
    public ResponseEntity<?> getTvSeriesById(@PathVariable long tvSeriesId) {
        try {
            TvSeriesInfoDTO seriesInfoDTO = tvSeriesService.getTvSeriesInfoById(tvSeriesId);
            SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), "Serie obtenida con exito", seriesInfoDTO);
            return ResponseEntity.ok(successResponse);
        } catch (TvSeriesNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Esta serie no se encuentra en nuestra base de datos", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
