/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlosrodriguez
 */
package cr.ac.una.ventas.business;

import cr.ac.una.ventas.data.MovieData;
import cr.ac.una.ventas.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieBusiness {

    @Autowired
    private MovieData movieData;

    public List<Movie> findAll() {
        return movieData.findAll();
    }

    public Movie findById(int id) {
        return movieData.findById(id);
    }

    public void save(Movie movie) {
        movieData.save(movie);
    }

    public void delete(int id) {
        movieData.delete(id);
    }
}
