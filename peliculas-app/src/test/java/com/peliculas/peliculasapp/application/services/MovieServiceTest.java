package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.application.ports.in.GetAndSaveMovieInfoUseCase;
import com.peliculas.peliculasapp.domain.dto.MovieDTO;
import com.peliculas.peliculasapp.domain.dto.MovieInfoDTO;
import com.peliculas.peliculasapp.domain.models.Genre;
import com.peliculas.peliculasapp.domain.models.ProductionCompany;
import com.peliculas.peliculasapp.domain.models.ProductionCountries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    private GetAndSaveMovieInfoUseCase getAndSaveMovieInfoUseCase;
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        getAndSaveMovieInfoUseCase = Mockito.mock(GetAndSaveMovieInfoUseCase.class);
        movieService = new MovieService(getAndSaveMovieInfoUseCase);
    }

    @Test
    public void testGetMovieById() {
        // arrange
        long movieId = 1;
        MovieInfoDTO expectedDto = createSampleMovieInfoDTO();
        Mockito.when(getAndSaveMovieInfoUseCase.getMovieById(movieId)).thenReturn(expectedDto);

        // act
        MovieInfoDTO result = movieService.getMovieById(movieId);

        // assert
        assertNotNull(result);
        assertEquals(expectedDto.getTitle(), result.getTitle());
        assertEquals(expectedDto.getHomepage(), result.getHomepage());
        assertEquals(expectedDto.getOverview(), result.getOverview());
        assertEquals(expectedDto.getRelease_date(), result.getRelease_date());
        assertEquals(expectedDto.getPopularity(), result.getPopularity());
        assertEquals(expectedDto.getStatus(), result.getStatus());
        assertEquals(expectedDto.getProduction_companies(), result.getProduction_companies());
        assertEquals(expectedDto.getProduction_countries(), result.getProduction_countries());
        assertEquals(expectedDto.getGenres(), result.getGenres());
        assertEquals(expectedDto.getVote_average(), result.getVote_average());
        assertEquals(expectedDto.getVote_count(), result.getVote_count());
        assertEquals(expectedDto.getRevenue(), result.getRevenue());
        assertEquals(expectedDto.getBudget(), result.getBudget());
        assertEquals(expectedDto.getPoster_path(), result.getPoster_path());
    }

    @Test
    public void testSaveMovieInfo() {
        // arrange
        long movieId = 1;
        MovieDTO expectedDto = createDummyMovieDTO();
        Mockito.when(getAndSaveMovieInfoUseCase.getAndSaveMovieInfo(movieId)).thenReturn(expectedDto);

        // act
        MovieDTO result = movieService.saveMovieInfo(movieId);

        // assert
        assertNotNull(result);
        assertEquals(expectedDto.getId(), result.getId());
        assertEquals(expectedDto.getTitle(), result.getTitle());
        assertEquals(expectedDto.getOverview(), result.getOverview());
        assertEquals(expectedDto.getRelease_date(), result.getRelease_date());
        assertEquals(expectedDto.getPopularity(), result.getPopularity());
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

    private MovieDTO createDummyMovieDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1);
        movieDTO.setTitle("dragon ball");
        movieDTO.setOverview("goku y vegeta");
        movieDTO.setRelease_date("2024-03-18");
        movieDTO.setPopularity(1000);
        return movieDTO;
    }
}