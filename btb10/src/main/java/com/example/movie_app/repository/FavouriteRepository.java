package com.example.movie_app.repository;

import com.example.movie_app.entity.Favourite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourite,Integer> {
}
