package com.example.movie_app.repository;

import com.example.movie_app.entity.Actors;
import com.example.movie_app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorsRepository extends JpaRepository<Actors,Integer> {
}
