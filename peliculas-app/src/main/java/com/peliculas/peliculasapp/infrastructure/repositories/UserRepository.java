package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);



}