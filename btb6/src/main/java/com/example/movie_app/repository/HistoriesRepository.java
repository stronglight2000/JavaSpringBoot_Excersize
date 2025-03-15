package com.example.movie_app.repository;

import com.example.movie_app.entity.Histories;
import com.example.movie_app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriesRepository extends JpaRepository<Histories,Integer> {
}
