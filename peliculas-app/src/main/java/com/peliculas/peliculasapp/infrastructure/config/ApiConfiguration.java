package com.peliculas.peliculasapp.infrastructure.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class ApiConfiguration {

    @Value("${EXTERNAL_API_KEY}")
    private String externalApiToken;

    @Value("${API_URL}")
    private String apiUrl;

    public String getExternalApiToken() {
        return externalApiToken;
    }

    public String getApiUrl() {
        return apiUrl;
    }

}