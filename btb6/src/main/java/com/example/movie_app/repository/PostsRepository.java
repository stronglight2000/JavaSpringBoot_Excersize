package com.example.movie_app.repository;

import com.example.movie_app.entity.Posts;
import com.example.movie_app.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Integer> {
}
