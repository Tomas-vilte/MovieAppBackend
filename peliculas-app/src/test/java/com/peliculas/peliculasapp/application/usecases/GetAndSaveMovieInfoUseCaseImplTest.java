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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        valueOperations = Mockito.mock(ValueOperations.class);

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        service = new GetAndSaveMovieInfoUseCaseImpl(
                movieDetailsAdapter,
                movieRepository,
                modelMapper,
                valueOperations
        );
    }
    @Test void testGetMovieByIdFromCache() {
        // Arrange
        long movieId = 1011985;
        MovieInfoDTO expectedMovieInfoDTO = createSampleMovieInfoDTO();

        when(valueOperations.get("movie:" + movieId)).thenReturn(expectedMovieInfoDTO);

        // Act
        MovieInfoDTO result = service.getMovieById(movieId);

        // Assert
        assertEquals(expectedMovieInfoDTO, result);
        verify(valueOperations, times(1)).get("movie:" + movieId);
        verify(movieRepository, never()).getMovieById(movieId);
    }
    @Test
    void testGetAndSaveMovieInfo() {
        // Arrange
        long movieId = 2;
        MovieDTO expectedMovieDTO = new MovieDTO();
        expectedMovieDTO.setTitle("Sample Title");
        expectedMovieDTO.setOverview("Sample Overview");
        expectedMovieDTO.setRelease_date("2024-03-18");
        expectedMovieDTO.setPopularity(8.5f);


        Movie movie = ofTesting();
        when(movieDetailsAdapter.getMovieInfoById(movieId)).thenReturn(movie);
        when(modelMapper.map(movie, MovieDTO.class)).thenReturn(expectedMovieDTO);

        // Act
        MovieDTO result = service.getAndSaveMovieInfo(movieId);

        // Assert
        assertEquals(expectedMovieDTO, result);
        verify(movieRepository, times(1)).saveMovie(movie);
    }

    @Test
    void testGetMovieByIdFromDatabase() {
        // Arrange
        long movieId = 1011985;
        MovieInfoDTO expectedMovieInfoDTO = createSampleMovieInfoDTO();

        Optional<Movie> optionalMovie = Optional.of(ofTesting());
        when(movieRepository.getMovieById(movieId)).thenReturn(optionalMovie);
        when(modelMapper.map(any(), eq(MovieInfoDTO.class))).thenReturn(expectedMovieInfoDTO);

        // act
        MovieInfoDTO result = service.getMovieById(movieId);

        // assert
        assertNotNull(result);
        assertEquals(expectedMovieInfoDTO, result);
        verify(redisTemplate, atMost(1)).opsForValue();
        verify(movieRepository, times(1)).getMovieById(movieId);
        verify(valueOperations, times(1)).set(eq("movie:" + movieId), eq(expectedMovieInfoDTO), eq(Duration.ofMinutes(1)));
    }

    @Test
    void testGetMovieByIdFromDatabaseWhenMovieDoesNotExist() {
        // Arrange
        long movieId = 1011985;

        Optional<Movie> optionalMovie = Optional.empty();
        when(movieRepository.getMovieById(movieId)).thenReturn(optionalMovie);

        // Act
        MovieInfoDTO result = service.getMovieById(movieId);

        // Assert
        assertNull(result);
        verify(redisTemplate, never()).opsForValue(); // Verificar que no se llama a opsForValue() si la película no se encuentra en la base de datos
        verify(movieRepository, times(1)).getMovieById(movieId);
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
    private Movie ofTesting() {
        long id = 1;
        String overview = "Descripcion de la pelicula";
        String status = "Estado de la pelcula";
        List<ProductionCompany> productionCompanies = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<ProductionCountries> productionCountries = new ArrayList<>();
        String title = "ttulo de la pelicula";
        float voteAverage = 4.5f;
        float voteCount = 1000;
        long revenue = 1000000;
        int budget = 500000;
        String posterPath = "/poster.jpg";
        float popularity = 7.8f;
        String releaseDate = "2024-03-18";

        return new Movie(id, overview, status, productionCompanies, genres, productionCountries, title, voteAverage, voteCount, revenue, budget, popularity, posterPath, releaseDate);
    }
}