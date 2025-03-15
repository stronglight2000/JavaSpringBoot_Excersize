package com.example.movie_app.repository;

import com.example.movie_app.entity.Medias;
import com.example.movie_app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediasRepository extends JpaRepository<Medias,Integer> {
}
