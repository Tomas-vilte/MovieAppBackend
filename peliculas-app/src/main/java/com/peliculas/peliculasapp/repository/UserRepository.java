package com.peliculas.peliculasapp.repository;
import com.peliculas.peliculasapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);



}