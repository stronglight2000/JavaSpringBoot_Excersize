package com.example.movie_app.repository;

import com.example.movie_app.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
