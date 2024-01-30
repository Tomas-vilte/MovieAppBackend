package com.peliculas.peliculasapp.infrastructure.controllers;
import com.peliculas.peliculasapp.dto.ReviewDTO;
import com.peliculas.peliculasapp.infrastructure.repositories.ReviewRepository;
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
    public List<ReviewDTO> getReviewsByContentId(@PathVariable Integer contentId) {
        return reviewRepository.getReviewsByContentId(contentId);
    }
}
