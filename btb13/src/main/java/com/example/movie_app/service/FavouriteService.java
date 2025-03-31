package com.example.movie_app.service;

import com.example.movie_app.entity.Favourite;
import com.example.movie_app.repository.FavouriteRepository;
import com.example.movie_app.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final MovieRepository movieRepository;

    private static final Integer USER_ID = 1; // Fix cứng userId

    // Lấy danh sách phim yêu thích theo trang
    public Page<Favourite> getFavourites(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return favouriteRepository.findByUserId(USER_ID, pageable);
    }

    // Xóa một bộ phim khỏi danh sách
    public void removeFavourite(Integer movieId) {
        favouriteRepository.deleteByUserIdAndMovieId(USER_ID, movieId);
    }

    // Xóa toàn bộ danh sách yêu thích
    public void removeAllFavourites() {
        favouriteRepository.deleteByUserId(USER_ID);
    }
}
