package com.example.movie_app.controller.admin;


import com.example.movie_app.model.request.CreateMovieRequest;
import com.example.movie_app.repository.ActorRepository;
import com.example.movie_app.repository.CountryRepository;
import com.example.movie_app.repository.DirectorRepository;
import com.example.movie_app.repository.GenreRepository;
import com.example.movie_app.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/movies")
public class MovieController {
    private final MovieService movieService;
    private final CountryRepository countryRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;
    @GetMapping
    public  String getIndexPage(){
        return "admin/movie/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "admin/movie/create";
    }

    @GetMapping("/{id}")
    public String getDetailPage(@PathVariable Long id){
        return "admin/movie/detail";
    }

    @PostMapping("/create")
    public String createMovie(@ModelAttribute CreateMovieRequest movieRequest) {
        movieService.createMovie(movieRequest);
        return "redirect:/admin/movies"; // Sau khi lưu xong sẽ chuyển về trang danh sách phim
    }
}
