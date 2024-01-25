package com.peliculas.peliculasapp.repository;
import com.peliculas.peliculasapp.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r, u.username, c.title FROM Review r " +
            "JOIN User u ON r.user.userId = u.userId " +
            "JOIN Content c ON r.content.contentId = c.contentId " +
            "WHERE c.contentId = :contentId")
    List<Review> getReviewsByContentId(@Param("contentId") Integer contentId);
}