package com.example.movie_app.repository;

import com.example.movie_app.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);


    List<Movie> findByNameContaining(String name);

    List<Movie> findByNameContainingIgnoreCase(String name);
    List<Movie> findByRatingBetween(Double min, Double max);
    List<Movie> findByRatingGreater(Double rating);

    // select * from movies where raing < ? order by desc
    List<Movie> findByRatingLessThanOrderByRatingDesc(Double rating);

    boolean existsByName(String name);

    long countByRating(Double rating);

    void deleteByName(String name);

}
