package com.example.movie_app.repository;

import com.example.movie_app.entity.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    // Chỉ lấy bài viết có status = true, sắp xếp theo ngày xuất bản mới nhất
    @Query("SELECT p FROM Post p WHERE p.status = true ORDER BY p.publishedAt DESC LIMIT :limit")
    List<Post> findLatestActivePosts(@Param("limit") int limit);

    Page<Post> findByStatusTrue(Pageable pageable);
}
