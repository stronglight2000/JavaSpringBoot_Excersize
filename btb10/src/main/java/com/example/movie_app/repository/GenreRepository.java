package com.example.movie_app.repository;


import com.example.movie_app.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
}
