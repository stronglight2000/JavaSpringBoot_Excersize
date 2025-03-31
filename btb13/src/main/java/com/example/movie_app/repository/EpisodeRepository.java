package com.example.movie_app.repository;

import com.example.movie_app.entity.Episode;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode,Integer> {
//    @Query("SELECT e FROM Episode e WHERE e.movie.id = :movieId AND e.status = :status ORDER BY e.displayOrder ASC")
    List<Episode> findByMovie_IdAndStatusOrderByDisplayOrderAsc(Integer id, boolean b);

    Episode findByMovie_IdAndDisplayOrderAndStatus(Integer id, Integer displayOrder, boolean status);

//    @Query("SELECT e FROM Episode e WHERE e.movie.id = :movieId AND e.status = true AND e.displayOrder = :displayOrder")
//    Optional<Episode> findEpisodeByMovie_IdAndDisplayOrder(@Param("movieId") Integer movieId, @Param("displayOrder") Integer displayOrder);
}
