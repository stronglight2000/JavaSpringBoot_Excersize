package com.example.movie_app.repository;

import com.example.movie_app.entity.Favourite;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite,Integer> {

    Page<Favourite> findByUserId(Integer userId, Pageable pageable);
    Optional<Favourite> findByUserIdAndMovieId(Integer userId, Integer movieId);
    void deleteByUserIdAndMovieId(Integer userId, Integer movieId);
    void deleteByUserId(Integer userId);

    boolean existsByUser_IdAndMovie_Id(Integer id, Integer movieId);

    Optional<Favourite> findByUserAndMovie(User user, Movie movie);

    boolean existsByUserAndMovie(User user, Movie movie);
    List<Favourite> findByUserId(Integer userId);
}
