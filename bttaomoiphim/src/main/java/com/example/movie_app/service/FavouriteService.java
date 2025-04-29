package com.example.movie_app.service;

import com.example.movie_app.entity.Favourite;
import com.example.movie_app.entity.Movie;
import com.example.movie_app.entity.User;
import com.example.movie_app.repository.FavouriteRepository;
import com.example.movie_app.repository.MovieRepository;
import com.example.movie_app.repository.UserRepository;
import jakarta.transaction.Transactional;
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

public class FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    private static final Integer USER_ID = 1; // Fix cứng userId

    // Lấy danh sách phim yêu thích theo trang
    public Page<Favourite> getFavourites(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
        return favouriteRepository.findByUserId(USER_ID, pageable);
    }

    // Xóa một bộ phim khỏi danh sách
    @Transactional
    public void removeFavourite(Integer movieId) {
        favouriteRepository.deleteByUserIdAndMovieId(USER_ID, movieId);
    }

    // Xóa toàn bộ danh sách yêu thích
    @Transactional
    public void removeAllFavourites() {
        // Kiểm tra user có tồn tại
//        User user = userRepository.findById(USER_ID)
//        .orElseThrow(() -> new
        favouriteRepository.deleteByUserId(USER_ID);
    }

    public boolean isFavourite(int userId, int movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        return user != null && movie != null && favouriteRepository.existsByUserAndMovie(user, movie);
    }

    public boolean addToFavourite(int userId, int movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user == null || movie == null) return false;

        if (!favouriteRepository.existsByUserAndMovie(user, movie)) {
            Favourite favorite = new Favourite();
            favorite.setUser(user);
            favorite.setMovie(movie);
            favorite.setCreatedAt(LocalDateTime.now());
            favouriteRepository.save(favorite);
            return true;
        }
        return false;
    }

    public List<Favourite> getFavouritesByUserId(Integer userId) {
        return favouriteRepository.findByUserId(userId);
    }
}
