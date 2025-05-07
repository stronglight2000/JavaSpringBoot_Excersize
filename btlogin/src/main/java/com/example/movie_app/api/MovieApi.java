package com.example.movie_app.api;

import com.example.movie_app.model.request.UpdateMovieRequest;
import com.example.movie_app.model.request.UpsertMovieRequest;
import com.example.movie_app.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/admin/movies")
@RequiredArgsConstructor
public class MovieApi {
    private final MovieService movieService;


    @GetMapping()
    ResponseEntity<?> getAllMovies(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(movieService.getAllMovies(page, pageSize));
    }

    @PostMapping
    ResponseEntity<?> createMovie(@Valid @RequestBody UpdateMovieRequest request) {
        return ResponseEntity.ok(movieService.createMovie(request));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateMovie(@Valid @RequestBody UpdateMovieRequest request,
                                  @PathVariable Integer id) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/upload-thumbnail")
    ResponseEntity<?> uploadThumbnail(@RequestParam MultipartFile file, @PathVariable Integer id) {
        return ResponseEntity.ok(movieService.uploadThumbnail(id, file));
    }
}
