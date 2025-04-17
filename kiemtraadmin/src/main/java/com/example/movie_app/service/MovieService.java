package com.example.movie_app.service;

import com.example.movie_app.entity.*;
import com.example.movie_app.exception.BadRequestException;
import com.example.movie_app.exception.NotFoundException;
import com.example.movie_app.model.enums.MovieType;
import com.example.movie_app.model.request.CreateMovieRequest;
import com.example.movie_app.model.request.UpdateMovieRequest;
import com.example.movie_app.repository.*;
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
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final CountryRepository countryRepository;

    public List<Movie> findHotMovie(Boolean status, Integer limit){
        return movieRepository.findHotMovie(status, limit);
    }
    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page -1,pageSize, Sort.by("publishedAt").descending());
        Page<Movie> moviePage = movieRepository.findByTypeAndStatus(type,status,pageable);
        return moviePage;

    }

    public Movie findMovieDetails(Integer id, String slug){
        return movieRepository.findByIdAndSlugAndStatus(id,slug,true);
    }

    public List<Movie> findRelatedMoviesByType(MovieType type, Integer excludeMovieId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return movieRepository.findRelatedMoviesByType(type, excludeMovieId, pageable);
    }


    public Page<Movie> getMovies(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findAll(pageable);
    }

    public Movie createMovie(CreateMovieRequest request) {
        // Validate Country
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy country"));

        // Validate Genres
        List<Genre> genres = genreRepository.findAllById(request.getGenreIds());
        if (genres.size() != request.getGenreIds().size()) {
            throw new BadRequestException("1 số Genre Id không hợp lệ");
        }

        // Validate Actors
        List<Actor> actors = actorRepository.findAllById(request.getActorIds());
        if (actors.size() != request.getActorIds().size()) {
            throw new BadRequestException("1 số actor Id không hợp lệ");
        }

        // Validate Directors
        List<Director> directors = directorRepository.findAllById(request.getDirectorIds());
        if (directors.size() != request.getDirectorIds().size()) {
            throw new BadRequestException("1 số director id không hợp lệ");
        }

        // Tạo movie mới
        Movie movie = Movie.builder()
                .name(request.getName())
                .trailer(request.getTrailerUrl())
                .description(request.getDescription())
                .releaseYear(request.getReleaseYear())
                .status(request.getStatus())
                .type(request.getType())
                .country(country)
                .genres(genres)
                .actors(actors)
                .directors(directors)
                .createdAt(LocalDateTime.now())
                .build();

        return movieRepository.save(movie);
    }

    public Movie updateMovie(Integer id, UpdateMovieRequest request) {
        // Kiểm tra movie có tồn tại không
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie with id = " + id + " not found"));

        // Validate Country
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new NotFoundException("Country not found"));

        // Validate Genres
        List<Genre> genres = genreRepository.findAllById(request.getGenreIds());
        if (genres.size() != request.getGenreIds().size()) {
            throw new BadRequestException("Some genre IDs are invalid");
        }

        // Validate Actors
        List<Actor> actors = actorRepository.findAllById(request.getActorIds());
        if (actors.size() != request.getActorIds().size()) {
            throw new BadRequestException("Some actor IDs are invalid");
        }

        // Validate Directors
        List<Director> directors = directorRepository.findAllById(request.getDirectorIds());
        if (directors.size() != request.getDirectorIds().size()) {
            throw new BadRequestException("Some director IDs are invalid");
        }

        // Cập nhật thông tin
        movie.setName(request.getName());
        movie.setTrailer(request.getTrailerUrl());
        movie.setDescription(request.getDescription());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setStatus(request.getStatus());
        movie.setType(request.getType());
        movie.setCountry(country);
        movie.setGenres(genres);
        movie.setActors(actors);
        movie.setDirectors(directors);
        movie.setUpdatedAt(LocalDateTime.now());

        return movieRepository.save(movie);
    }

    public void deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Phim không tồn tại"));
        movieRepository.delete(movie);
    }

    private void mapRequestToMovie(CreateMovieRequest request, Movie movie) {
        movie.setName(request.getName());
        movie.setTrailer(request.getTrailerUrl());
        movie.setDescription(request.getDescription());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setType(request.getType());
        movie.setStatus(request.getStatus());
        movie.setCountry(countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new NotFoundException("Country không tồn tại")));
        movie.setGenres(genreRepository.findAllById(request.getGenreIds()));
        movie.setActors(actorRepository.findAllById(request.getActorIds()));
        movie.setDirectors(directorRepository.findAllById(request.getDirectorIds()));
    }

    public Page<Movie> getAllMovies(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findAll(pageable);
    }
}
