package com.example.movie_app.service;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.entity.Review;
import com.example.movie_app.entity.User;
import com.example.movie_app.repository.MovieRepository;
import com.example.movie_app.repository.ReviewRepository;
import com.example.movie_app.repository.UserRepository;
import com.example.movie_app.model.request.CreateReviewRequest;
import com.example.movie_app.model.request.UpdateReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    public Page<Review> getReviewsByMovie(Integer movieId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page-1,pageSize, Sort.by("createdAt").descending());
        return reviewRepository.findByMovie_Id(movieId,pageable);
    }

    public Review createReview(CreateReviewRequest request) {
        //TODO: Fix login user
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user có id =" +userId));

        Movie movie = movieRepository.findByIdAndStatusTrue(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phim có id =" +request.getMovieId()));
        return null;
    }

    public Review updateReview(Integer id, UpdateReviewRequest request) {
        // TODO: Fix login user
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user có id = " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy review có id = " + id));

        // Check user is owner of review
        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Không có quyền cập nhật review");
        }

        review.setContent(request.getContent());
        review.setRating(request.getRating());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        // TODO: Fix login user
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user có id = " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy review có id = " + id));

        // Check user is owner of review
        if (!review.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Không có quyền xóa review");
        }
        reviewRepository.delete(review);
    }
}
