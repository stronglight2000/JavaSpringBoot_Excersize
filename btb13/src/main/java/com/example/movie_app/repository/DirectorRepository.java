package com.example.movie_app.repository;

import com.example.movie_app.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director,Integer> {
}
