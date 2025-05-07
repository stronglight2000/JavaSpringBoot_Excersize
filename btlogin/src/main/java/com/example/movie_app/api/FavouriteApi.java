package com.example.movie_app.api;

import com.example.movie_app.entity.Favourite;
import com.example.movie_app.service.FavouriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/favourites")
@RequiredArgsConstructor
public class FavouriteApi {
    private final FavouriteService favouriteService;

    @GetMapping
    public ResponseEntity<Page<Favourite>> getFavourites(@RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "10") int pageSize) {
        Page<Favourite> favouriteMovies = favouriteService.getFavourites(page, pageSize);
        return ResponseEntity.ok(favouriteMovies);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFavourite(@RequestBody Map<String, Integer> request) {
        Integer movieId = request.get("movieId");
        if (movieId == null) {
            return ResponseEntity.badRequest().body("Movie ID is required");
        }
        favouriteService.removeFavourite(movieId);
        return ResponseEntity.ok().body("Removed movie from favourites");
    }

    @DeleteMapping("/removeAll")
    public ResponseEntity<?> removeAllFavourites() {
        favouriteService.removeAllFavourites();
        return ResponseEntity.ok().body("Removed all favourite movies");
    }
}
