package com.example.movie_app.repository;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);


    List<Movie> findByNameContaining(String name);

    //Page bắt đầu = 0 tương ứng với trang 1
    Page<Movie> findByNameContaining(String name, Pageable pageable);

    List<Movie> findByNameContainingIgnoreCase(String name);
    List<Movie> findByRatingBetween(Double min, Double max);
    List<Movie> findByRatingGreaterThan(Double rating);

    List<Movie> findByRatingLessThan(Double rating, Sort sort);
    // C1: Method query: Doc lap vs CSDL
    // select * from movies where rating < ? order by rating desc
//    List<Movie> findByRatingLessThanOrderByRatingDesc(Double rating);

    // C2: native query: Phu thuoc vao CSDL
    @Query(value = "select * from movies where rating < ?1 order by rating desc", nativeQuery = true)
    List<Movie> findByRatingLessThanOrderByRatingDesc_NQ1(Double rating);

    @Query(value = "select * from movies where rating < :rating order by rating desc", nativeQuery = true)
    List<Movie> findByRatingLessThanOrderByRatingDesc_NQ2(@Param("rating") Double rating);

    // C3: JPQL: Doc lap vs CSDL
    @Query(value = "select m from Movie m where m.rating < ?1 order by m.rating desc")
    List<Movie> findByRatingLessThanOrderByRatingDesc_JPQL(Double rating);

    // select * from movies where raing < ? order by desc
    List<Movie> findByRatingLessThanOrderByRatingDesc(Double rating);

    boolean existsByName(String name);

    long countByRating(Double rating);

    void deleteByName(String name);
    List<Movie> findByStatusTrue();

    Page<Movie> findByTypeAndStatus(MovieType type, Boolean status, Pageable pageable);

    @Query(value = "select * from movies where status =?1 order by rating desc limit ?2", nativeQuery = true)
    List<Movie> findHotMovie(Boolean status, Integer limit);

    @Query(value = "select * from movies where id = :id AND slug = :slug AND status = :status", nativeQuery = true)
    Movie findByIdAndSlugAndStatus(Integer id, String slug, boolean b);
}
