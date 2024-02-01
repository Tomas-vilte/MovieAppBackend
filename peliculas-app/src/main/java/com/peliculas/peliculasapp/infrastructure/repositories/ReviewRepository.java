package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.dto.ReviewDTO;
import com.peliculas.peliculasapp.infrastructure.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

    @Query(value = """
            SELECT new com.peliculas.peliculasapp.dto.ReviewDTO(r.id, r.rating, r.comment, u.username, c.title)
            FROM Review r
            JOIN r.user u
            JOIN r.content c
            WHERE c.id = :contentId
        """)
    List<ReviewDTO> getReviewsByContentId(@Param("contentId") Integer contentId);
}