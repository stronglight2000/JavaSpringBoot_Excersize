package com.example.movie_app.repository;

import com.example.movie_app.entity.Episodes;
import com.example.movie_app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodesRepository extends JpaRepository<Episodes,Integer> {
}
