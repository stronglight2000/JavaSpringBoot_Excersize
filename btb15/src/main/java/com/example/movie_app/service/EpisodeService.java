package com.example.movie_app.service;

import com.example.movie_app.entity.Episode;
import com.example.movie_app.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;


    public List<Episode> findEpisodesByMovieId(Integer id){
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(id,true);
    }

    // // full = 1 || 1,2,3
    public Episode findEpisodeByDisplayOrder(Integer id, String tap) {
        Integer displayOrder = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRepository
                .findByMovie_IdAndDisplayOrderAndStatus(id, displayOrder, true);
    }
}
