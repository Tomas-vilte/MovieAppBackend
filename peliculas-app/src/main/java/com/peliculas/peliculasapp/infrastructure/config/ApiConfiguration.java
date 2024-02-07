package com.peliculas.peliculasapp.infrastructure.config;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    private final String externalApiToken = System.getenv("EXTERNAL_API_KEY");

    private final String apiUrl = System.getenv("API_URL");

    public void print() {
        System.out.println("external" + externalApiToken);
    }

    public String getExternalApiToken() {
        return externalApiToken;
    }

    public String getApiUrl() {
        return apiUrl;
    }

}