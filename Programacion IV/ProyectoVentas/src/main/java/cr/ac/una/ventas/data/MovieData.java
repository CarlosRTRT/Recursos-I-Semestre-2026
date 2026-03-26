/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ventas.data;

/**
 *
 * @author carlosrodriguez
 */

import cr.ac.una.ventas.model.Category;
import cr.ac.una.ventas.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MovieData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Movie> movieRowMapper = new RowMapper<Movie>() {
        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie = new Movie();
            movie.setId(rs.getInt("m.id"));
            movie.setTitle(rs.getString("m.title"));
            movie.setReleaseYear(rs.getInt("m.release_year"));
            movie.setPhotoUrl(rs.getString("m.photo_url"));

            int catId = rs.getInt("m.category_id");
            if (!rs.wasNull()) {
                movie.setCategoryId(catId);
                Category cat = new Category();
                cat.setId(catId);
                cat.setName(rs.getString("c.name"));
                movie.setCategory(cat);
            }
            return movie;
        }
    };

    public List<Movie> findAll() {
        String sql = "SELECT m.*, c.name FROM movie m LEFT JOIN category c ON m.category_id = c.id ORDER BY m.title";
        return jdbcTemplate.query(sql, movieRowMapper);
    }

    public Movie findById(int id) {
        String sql = "SELECT m.*, c.name FROM movie m LEFT JOIN category c ON m.category_id = c.id WHERE m.id = ?";
        List<Movie> results = jdbcTemplate.query(sql, movieRowMapper, id);
        return results.isEmpty() ? null : results.get(0);
    }

    public int save(Movie movie) {
        if (movie.getId() == 0) {
            String sql = "INSERT INTO movie (title, release_year, photo_url, category_id) VALUES (?, ?, ?, ?)";
            return jdbcTemplate.update(sql,
                    movie.getTitle(),
                    movie.getReleaseYear(),
                    movie.getPhotoUrl(),
                    movie.getCategoryId());
        } else {
            String sql = "UPDATE movie SET title = ?, release_year = ?, photo_url = ?, category_id = ? WHERE id = ?";
            return jdbcTemplate.update(sql,
                    movie.getTitle(),
                    movie.getReleaseYear(),
                    movie.getPhotoUrl(),
                    movie.getCategoryId(),
                    movie.getId());
        }
    }

    public int delete(int id) {
        String sql = "DELETE FROM movie WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
