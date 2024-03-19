package com.peliculas.peliculasapp.application.usecases;

import com.peliculas.peliculasapp.application.ports.in.GetAndSaveMovieInfoUseCase;
import com.peliculas.peliculasapp.application.ports.out.MovieRepositoryPort;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.domain.models.Movie;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import com.peliculas.peliculasapp.dto.MovieDTO;
import com.peliculas.peliculasapp.dto.MovieInfoDTO;
import com.peliculas.peliculasapp.infrastructure.adapters.MovieDetailsAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetAndSaveMovieInfoUseCaseImplTest {
    private MovieDetailsAdapter movieDetailsAdapter;
    private MovieRepositoryPort movieRepository;
    private ModelMapper modelMapper;
    private RedisTemplate<String, Object> redisTemplate;
    private GetAndSaveMovieInfoUseCase service;
    private ValueOperations<String, Object> valueOperations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movieDetailsAdapter = Mockito.mock(MovieDetailsAdapter.class);
        movieRepository = Mockito.mock(MovieRepositoryPort.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        redisTemplate = Mockito.mock(RedisTemplate.class);

        service = new GetAndSaveMovieInfoUseCaseImpl(
                movieDetailsAdapter,
                movieRepository,
                modelMapper,
                redisTemplate
        );
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void testGetMovieByIdFromDatabase() {
        // Arrange
        long movieId = 123;
        MovieInfoDTO expectedMovieInfoDTO = createSampleMovieInfoDTO();
        Optional<Movie> optionalMovie = Optional.of(Movie.ofTesting()); // Mock your Optional<Movie> here

        when(valueOperations.get(anyString())).thenReturn(null);
        when(movieRepository.getMovieById(anyLong())).thenReturn(optionalMovie);
        when(modelMapper.map(optionalMovie, MovieInfoDTO.class)).thenReturn(expectedMovieInfoDTO);

        // Act
        MovieInfoDTO result = service.getMovieById(movieId);

        // Assert
        assertEquals(expectedMovieInfoDTO, result);
        verify(valueOperations, times(1)).get("movie:" + movieId);
        verify(valueOperations, times(1)).set("movie:" + movieId, expectedMovieInfoDTO);
        verify(movieRepository, times(1)).getMovieById(movieId);
        verify(modelMapper, times(1)).map(optionalMovie, MovieInfoDTO.class);
    }

    private MovieInfoDTO createSampleMovieInfoDTO() {
        MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
        movieInfoDTO.setTitle("Inception");
        movieInfoDTO.setHomepage("https://www.warnerbros.com/movies/inception/");
        movieInfoDTO.setOverview("Inception is a 2010 science fiction action film written and directed by Christopher Nolan, who also produced the film with Emma Thomas, his wife.");
        movieInfoDTO.setRelease_date("2010-07-16");
        movieInfoDTO.setPopularity(42.96f);
        movieInfoDTO.setStatus("Released");

        List<ProductionCompany> productionCompanies = new ArrayList<>();
        ProductionCompany productionCompany = new ProductionCompany();
        productionCompany.setName("Warner Bros. Pictures");
        productionCompanies.add(productionCompany);
        movieInfoDTO.setProduction_companies(productionCompanies);

        List<ProductionCountries> productionCountries = new ArrayList<>();
        ProductionCountries productionCountry = new ProductionCountries("dad", "23");
        productionCountry.setName("United States of America");
        productionCountries.add(productionCountry);
        movieInfoDTO.setProduction_countries(productionCountries);

        List<Genre> genres = new ArrayList<>();
        Genre genre = new Genre();
        genre.setName("Action");
        genres.add(genre);
        movieInfoDTO.setGenres(genres);

        movieInfoDTO.setVote_average(8.8f);
        movieInfoDTO.setVote_count(29390);
        movieInfoDTO.setRevenue(829000000);
        movieInfoDTO.setBudget(160000000);
        movieInfoDTO.setPoster_path("/poster.jpg");

        return movieInfoDTO;
    }
}