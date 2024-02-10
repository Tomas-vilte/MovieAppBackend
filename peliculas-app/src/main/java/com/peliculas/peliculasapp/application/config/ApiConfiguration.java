package com.peliculas.peliculasapp.application.config;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiConfiguration {
    private static final Dotenv dotenv = Dotenv.configure().load();

    public String getApiUrl() {
        return dotenv.get("API_URL");
    }

    public String getApiKey() { return dotenv.get("EXTERNAL_API_KEY");}

    public static void printAllEnvVariables() {
        System.out.println("Variables de entorno:");
        dotenv.entries().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
