package com.peliculas.peliculasapp.controller;


import com.peliculas.peliculasapp.models.Review;
import com.peliculas.peliculasapp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/byContentId/{contentId}")
    public List<Review> getReviewsByContentId(@PathVariable Integer contentId) {
        return reviewRepository.getReviewsByContentId(contentId);
    }
}
