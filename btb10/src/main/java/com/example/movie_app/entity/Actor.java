package com.example.movie_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "actors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String slug;
    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
