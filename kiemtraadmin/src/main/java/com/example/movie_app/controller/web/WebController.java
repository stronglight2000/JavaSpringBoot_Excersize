package com.example.movie_app.controller.web;

import com.example.movie_app.entity.*;
import com.example.movie_app.model.enums.MovieType;
import com.example.movie_app.service.EpisodeService;
import com.example.movie_app.service.FavouriteService;
import com.example.movie_app.service.MovieService;
import com.example.movie_app.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final PostService postService;
    private final EpisodeService episodeService;
    private final FavouriteService favouriteService;
    private final HttpSession session;
    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Movie> hotMovies = movieService.findHotMovie(true,4);
        List<Movie> phimLeList = movieService.findByType(MovieType.PHIM_LE,true,1,6).getContent();
        List<Movie> phimBoList = movieService.findByType(MovieType.PHIM_BO,true,1,6).getContent();
        List<Movie> phimChieuRapList = movieService.findByType(MovieType.PHIM_CHIEU_RAP,true,1,6).getContent();

        // Chỉ lấy các bài viết có status = true
        List<Post> postList = postService.getLatestActivePosts(4);
        model.addAttribute("hotMovies",hotMovies);
        model.addAttribute("phimLeList",phimLeList);
        model.addAttribute("phimBoList",phimBoList);
        model.addAttribute("phimChieuRapList",phimChieuRapList);
        model.addAttribute("postList", postList);
        return "index";
    }
    @GetMapping("/phim-bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "18") Integer pageSize,
                                      Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "phim-chieu-rap";
    }



    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id,@PathVariable String slug,Model model){
        Movie movie = movieService.findMovieDetails(id, slug);
        model.addAttribute("movie",movie);
        // Kiểm tra nếu movie không tồn tại
        if (movie == null) {
            return "redirect:/404"; // hoặc return "error"; nếu bạn có trang lỗi
        }


        // Lấy danh sách tập phim(movieId, status = true, sort by displayOrder asc)
        List<Episode> episodes = episodeService.findEpisodesByMovieId(id);
        model.addAttribute("episodes",episodes);

        // Danh sách phim liên quan (cùng loại, rating giảm dần, status=true, tối đa 6)
        List<Movie> relatedMovies = movieService.findRelatedMoviesByType(movie.getType(), id, 6);
        model.addAttribute("relatedMovies", relatedMovies);

        return "chi-tiet-phim";
    }

    @GetMapping("/xem-phim/{id}/{slug}")
    public String getWatchMovieDetailsPage(@PathVariable Integer id,@PathVariable String slug,Model model,@RequestParam String tap){
        //Thông tin chi tiết phim
        Movie movie = movieService.findMovieDetails(id, slug);
        model.addAttribute("movie",movie);


        // Lấy danh sách tập phim(movieId, status = true, sort by displayOrder asc)
        List<Episode> episodes = episodeService.findEpisodesByMovieId(id);
        model.addAttribute("episodes",episodes);
        return "xem-phim";
    }


    @GetMapping("/tin-tuc")
    public String getNewsPage(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "6") int size,
                              Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("publishedAt").descending());
        Page<Post> postPage = postService.getActivePosts(pageable);
//        Page<Post> postPage = postService.getActivePosts(pageable);

        model.addAttribute("postPage", postPage);
        return "tin-tuc";
    }



    @GetMapping("/phim-yeu-thich")
    public String getFavouriteMovies(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int pageSize,
                                     Model model) {
        Page<Favourite> favourites = favouriteService.getFavourites(page, pageSize);
        model.addAttribute("favourites", favourites);
        return "phim-yeu-thich";
    }


    @PostMapping("/yeu-thich/them")
    public String addToFavorites(@RequestParam("movieId") Integer movieId,
                                 @RequestParam("userId") Integer userId,
                                 RedirectAttributes redirectAttributes) {
        favouriteService.addToFavourite(userId, movieId);
        redirectAttributes.addFlashAttribute("successMessage", "Đã thêm vào danh sách yêu thích!");
        return "redirect:/phim-yeu-thich";
    }

    @GetMapping("/dang-nhap")
    public String showLoginPage() {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {

            return "login";
        }
        return "redirect:/";

    }



}
