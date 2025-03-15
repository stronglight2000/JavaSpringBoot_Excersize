package com.example.movie_app.repository;

import com.example.movie_app.entity.Reviews;
import com.example.movie_app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews,Integer> {
}
