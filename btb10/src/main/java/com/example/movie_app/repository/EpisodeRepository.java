package com.example.movie_app.repository;

import com.example.movie_app.entity.Episode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode,Integer> {
}
