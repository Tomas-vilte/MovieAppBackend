package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieReviewEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, Long> {
    @Query(value = "SELECT r FROM MovieReviewEntity r " +
            "JOIN FETCH r.user u " +
            "JOIN FETCH r.movie m " +
            "WHERE r.movie.id = :movieId")
    List<MovieReviewEntity> findReviewsByMovieId(@Param("movieId") long movieId);
}
