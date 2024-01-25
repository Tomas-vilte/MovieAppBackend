package com.peliculas.peliculasapp.repository;
import com.peliculas.peliculasapp.dto.ReviewDTO;
import com.peliculas.peliculasapp.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query(value = """
            SELECT new com.peliculas.peliculasapp.dto.ReviewDTO(r.id, r.rating, r.comment, u.username, c.title)
            FROM Review r
            JOIN r.user u
            JOIN r.content c
            WHERE c.id = :contentId
        """)
    List<ReviewDTO> getReviewsByContentId(@Param("contentId") Integer contentId);
}