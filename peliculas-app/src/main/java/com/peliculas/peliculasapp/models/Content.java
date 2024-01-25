package com.peliculas.peliculasapp.models;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "genre")
    private String genre;

    @Column(name = "content_type", nullable = false)
    private String contentType;


    // Constructor privado para evitar la creacion de instancias
    private Content() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getContentType() {
        return contentType;
    }

    public static class Builder {
        private Content content;

        public Builder() {
            this.content = new Content();
        }

        public Builder setContentId(int contentId) {
            this.content.id = contentId;
            return this;
        }

        public Builder setTitle(String title) {
            this.content.title = title;
            return this;
        }

        public Builder setReleaseDate(Date releaseDate) {
            this.content.releaseDate = releaseDate;
            return this;
        }

        public Builder setGenre(String genre) {
            this.content.genre = genre;
            return this;
        }

        public Builder setContentType(String contentType) {
            this.content.contentType = contentType;
            return this;
        }

        public Content build() {
            return this.content;
        }
    }
}
