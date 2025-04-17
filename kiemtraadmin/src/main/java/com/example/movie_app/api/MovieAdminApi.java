package com.example.movie_app.api;


import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.request.CreateMovieRequest;
import com.example.movie_app.model.request.UpdateMovieRequest;
import com.example.movie_app.model.request.UpsertMovieRequest;
import com.example.movie_app.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class MovieAdminApi {
    private final MovieService movieService;

    // 1. Lấy danh sách phim có phân trang
    @GetMapping
    public ResponseEntity<?> getAllMovies(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Movie> moviePage = movieService.getAllMovies(page, pageSize);
        return ResponseEntity.ok(moviePage);
    }

    // 2. Tạo mới phim
    @PostMapping
    public ResponseEntity<?> createMovie(@Valid @RequestBody CreateMovieRequest request) {
        Movie movie = movieService.createMovie(request);
        return ResponseEntity.ok(movie);
    }

    // 3. Cập nhật phim
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id,
                                         @Valid @RequestBody UpdateMovieRequest request) {
        Movie movie = movieService.updateMovie(id, request);
        return ResponseEntity.ok(movie);
    }

    // 4. Xóa phim
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }


}
