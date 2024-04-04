package com.peliculas.peliculasapp.infrastructure.adapter.repositories;
import com.peliculas.peliculasapp.infrastructure.adapter.entities.MovieReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MovieReviewRepository extends JpaRepository<MovieReviewEntity, Long> {
}
