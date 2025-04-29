package com.example.movie_app.service;

import com.example.movie_app.entity.Episode;
import com.example.movie_app.entity.Movie;
import com.example.movie_app.exception.NotFoundException;
import com.example.movie_app.model.request.CreateEpisodeRequest;
import com.example.movie_app.model.request.UpdateEpisodeRequest;
import com.example.movie_app.repository.EpisodeRepository;
import com.example.movie_app.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final MovieRepository movieRepository;


    public List<Episode> findEpisodesByMovieId(Integer id){
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(id,true);
    }

    // // full = 1 || 1,2,3
    public Episode findEpisodeByDisplayOrder(Integer id, String tap) {
        Integer displayOrder = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRepository
                .findByMovie_IdAndDisplayOrderAndStatus(id, displayOrder, true);
    }



    public Page<Episode> getEpisodesByMovie(Integer movieId, int page, int pageSize) {
        if (!movieRepository.existsById(movieId)) {
            throw new NotFoundException("Movie ID không tồn tại");
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("displayOrder"));
        return episodeRepository.findByMovie_Id(movieId, pageable);
    }


    public Episode createEpisode(CreateEpisodeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy movieId"));

        Episode episode = new Episode();
        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        episode.setMovie(movie);
        episode.setCreatedAt(LocalDateTime.now());

        return episodeRepository.save(episode);
    }


    public Episode updateEpisode(Integer id, UpdateEpisodeRequest request) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy tập phim với id = " + id));

        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        episode.setUpdatedAt(LocalDateTime.now());

        return episodeRepository.save(episode);
    }


    public void deleteEpisode(Integer id) {
        if (!episodeRepository.existsById(id)) {
            throw new NotFoundException("Không tìm thấy tập phim để xoá");
        }
        episodeRepository.deleteById(id);
    }
}
