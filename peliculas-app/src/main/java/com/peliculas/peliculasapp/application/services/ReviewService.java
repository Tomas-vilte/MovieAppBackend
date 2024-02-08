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
        return reviewRepository.findReviewById(contentId);
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAllReviews();
    }
}
