package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.dto.ReviewDTO;
import com.peliculas.peliculasapp.infrastructure.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

    @Query("SELECT new com.peliculas.peliculasapp.dto.ReviewDTO(r.id, r.rating, r.comment, u.username, c.title, r.createdAt, r.updatedAt) "
            + "FROM ReviewEntity r JOIN r.user u JOIN r.content c")
    List<ReviewDTO> findAllReviews();

    @Query("SELECT new com.peliculas.peliculasapp.dto.ReviewDTO(r.id, r.rating, r.comment, u.username, c.title, r.createdAt, r.updatedAt) "
            + "FROM ReviewEntity r JOIN r.user u JOIN r.content c WHERE r.id = :id")
    Optional<ReviewDTO> findReviewById(@Param("id") int id);
}