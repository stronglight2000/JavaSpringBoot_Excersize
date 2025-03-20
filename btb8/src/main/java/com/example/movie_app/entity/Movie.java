package com.example.movie_app.entity;

import com.example.movie_app.enums.MovieType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String name;

    private String slug;

    @Column(columnDefinition =  "TEXT")
    private String description;
    private String thumbnail;
    private Integer releaseYear;
    private Boolean status;
    private String trailer;

    @Column(columnDefinition = "double default 5.0")
    private Double rating;
    // Type:
    @Enumerated(EnumType.STRING)
    private MovieType type;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;


}
