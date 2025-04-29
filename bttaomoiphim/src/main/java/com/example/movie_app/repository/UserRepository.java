package com.example.movie_app.repository;

import com.example.movie_app.entity.User;
import com.example.movie_app.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByRole(UserRole admin);
    Optional<User> findByEmail(String email);
}
