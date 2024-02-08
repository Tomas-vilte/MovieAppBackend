package com.peliculas.peliculasapp.infrastructure.entities;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "content")
public class ContentEntity {
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

    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews;


    // Constructor privado para evitar la creacion de instancias
    private ContentEntity() {}

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
        private ContentEntity content;

        public Builder() {
            this.content = new ContentEntity();
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

        public ContentEntity build() {
            return this.content;
        }
    }
}
