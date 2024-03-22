package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.infrastructure.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
