package com.peliculas.peliculasapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class PeliculasAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeliculasAppApplication.class, args);
	}
}

