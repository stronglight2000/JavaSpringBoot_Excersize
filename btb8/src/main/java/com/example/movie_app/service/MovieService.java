package com.example.movie_app.service;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.enums.MovieType;
import com.example.movie_app.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page -1,pageSize, Sort.by("publishedAt").descending());
        Page<Movie> moviePage = movieRepository.findByTypeAndStatus(type,status,pageable);
        return moviePage;

    }
}
