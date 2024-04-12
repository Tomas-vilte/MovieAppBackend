package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.domain.models.MovieReview;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieReviewEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, Long> {
    @Transactional(readOnly = true)
    @Query(value = "SELECT r FROM MovieReviewEntity r " +
            "JOIN FETCH r.user u " +
            "JOIN FETCH r.movie m " +
            "WHERE r.movie.id = :movieId")
    List<MovieReviewEntity> findAllReviewsByMovieId(@Param("movieId") final long movieId);

    @Transactional(readOnly = true)
    Optional<MovieReview> getMovieReviewById(final long reviewId);

}
