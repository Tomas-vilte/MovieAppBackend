package com.peliculas.peliculasapp.infrastructure.repositories;
import com.peliculas.peliculasapp.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);



}