package com.example.movie_app.repository;

import com.example.movie_app.entity.Favourites;
import com.example.movie_app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouritesRepository extends JpaRepository<Favourites,Integer> {
}
