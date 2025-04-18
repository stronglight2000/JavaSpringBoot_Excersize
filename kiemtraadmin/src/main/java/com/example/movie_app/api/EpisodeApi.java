package com.example.movie_app.api;


import com.example.movie_app.entity.Episode;
import com.example.movie_app.model.request.CreateEpisodeRequest;
import com.example.movie_app.model.request.UpdateEpisodeRequest;
import com.example.movie_app.service.EpisodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class EpisodeApi {
    private final EpisodeService episodeService;

    @GetMapping
    public ResponseEntity<?> getEpisodes(@RequestParam Integer movieId,
                                         @RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Episode> episodes = episodeService.getEpisodesByMovie(movieId, page, pageSize);
        return ResponseEntity.ok(episodes);
    }

    @PostMapping
    public ResponseEntity<?> createEpisode(@Valid @RequestBody CreateEpisodeRequest request) {
        Episode episode = episodeService.createEpisode(request);
        return ResponseEntity.ok(episode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEpisode(@PathVariable Integer id,
                                           @Valid @RequestBody UpdateEpisodeRequest request) {
        Episode episode = episodeService.updateEpisode(id, request);
        return ResponseEntity.ok(episode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id);
        return ResponseEntity.ok().build();
    }
}
