package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveInfoUseCase;
import org.springframework.stereotype.Service;


@Service
public class MovieService {
    private final GetAndSaveInfoUseCase getAndSaveInfoUseCase;

    public MovieService(GetAndSaveInfoUseCase getAndSaveInfoUseCase) {
        this.getAndSaveInfoUseCase = getAndSaveInfoUseCase;
    }

    public void saveMovieInfo(long movieId) {
        getAndSaveInfoUseCase.getAndSaveMovieInfo(movieId);
    }
}
