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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Category> categoryRowMapper = new RowMapper<Category>() {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category cat = new Category();
            cat.setId(rs.getInt("id"));
            cat.setName(rs.getString("name"));
            cat.setDescription(rs.getString("description"));
            return cat;
        }
    };

    public List<Category> findAll() {
        String sql = "SELECT * FROM category ORDER BY name";
        return jdbcTemplate.query(sql, categoryRowMapper);
    }

    public Category findById(int id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<Category> results = jdbcTemplate.query(sql, categoryRowMapper, id);
        return results.isEmpty() ? null : results.get(0);
    }

    public int save(Category category) {
        if (category.getId() == 0) {
            String sql = "INSERT INTO category (name, description) VALUES (?, ?)";
            return jdbcTemplate.update(sql, category.getName(), category.getDescription());
        } else {
            String sql = "UPDATE category SET name = ?, description = ? WHERE id = ?";
            return jdbcTemplate.update(sql, category.getName(), category.getDescription(), category.getId());
        }
    }

    public int delete(int id) {
        // Desvincular películas de esta categoría antes de borrar
        jdbcTemplate.update("UPDATE movie SET category_id = NULL WHERE category_id = ?", id);
        String sql = "DELETE FROM category WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
