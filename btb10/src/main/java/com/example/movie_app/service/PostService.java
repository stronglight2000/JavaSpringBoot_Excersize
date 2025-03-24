package com.example.movie_app.service;

import com.example.movie_app.entity.Post;
import com.example.movie_app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getLatestActivePosts(int limit) {
        return postRepository.findLatestActivePosts(limit);
    }

    public Page<Post> getActivePosts(Pageable pageable) {
        return postRepository.findByStatusTrue(pageable);
    }
}
