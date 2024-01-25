package com.peliculas.peliculasapp.repository;
import com.peliculas.peliculasapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);



}