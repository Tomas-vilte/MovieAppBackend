package com.peliculas.peliculasapp.application.services;
import com.peliculas.peliculasapp.dto.ReviewDTO;
import com.peliculas.peliculasapp.infrastructure.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public Optional<ReviewDTO> getReviewsByContentId(int contentId) {
        // TODO: add function
        return null;
    }

    public List<ReviewDTO> getAllReviews() {
        // TODO: add function
        return null;
    }
}
