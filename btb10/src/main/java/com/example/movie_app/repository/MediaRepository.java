package com.example.movie_app.repository;

import com.example.movie_app.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media,Integer> {
}
