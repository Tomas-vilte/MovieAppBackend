package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}