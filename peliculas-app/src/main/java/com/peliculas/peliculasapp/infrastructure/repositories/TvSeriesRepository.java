package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.infrastructure.entities.TvSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface TvSeriesRepository extends JpaRepository<TvSeriesEntity, Long> {
    @Transactional(readOnly = true)
    boolean existsByName(String name);
}