package cr.ac.una.ventas.controller;

import cr.ac.una.ventas.business.CategoryBusiness;
import cr.ac.una.ventas.business.MovieBusiness;
import cr.ac.una.ventas.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
 
@Controller
@RequestMapping("/movies")
public class MovieController {
 
    @Autowired
    private MovieBusiness movieBusiness;
 
    @Autowired
    private CategoryBusiness categoryBusiness;
 
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("movies", movieBusiness.findAll());
        return "movie/list";
    }
 
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("categories", categoryBusiness.findAll());
        model.addAttribute("formTitle", "Nueva Película");
        return "movie/formMovie";
    }
 
    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Movie movie = movieBusiness.findById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("categories", categoryBusiness.findAll());
        model.addAttribute("formTitle", "Editar Película");
        return "movie/formMovie";
    }
 
    @PostMapping("/save")
    public String save(@ModelAttribute Movie movie) {
        movieBusiness.save(movie);
        return "redirect:/movies/";
    }
 
    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        movieBusiness.delete(id);
        return "redirect:/movies/";
    }
}
