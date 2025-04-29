package com.example.movie_app.repository;

import com.example.movie_app.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Page<Review> findByMovie_Id(Integer movieId, Pageable pageable);
}
