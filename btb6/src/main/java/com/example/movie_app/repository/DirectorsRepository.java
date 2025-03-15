package com.example.movie_app.repository;

import com.example.movie_app.entity.Directors;
import com.example.movie_app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorsRepository extends JpaRepository<Directors,Integer> {
}
