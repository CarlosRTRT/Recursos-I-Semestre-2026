/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ventas.business;

/**
 *
 * @author carlosrodriguez
 */

import cr.ac.una.ventas.data.CategoryData;
import cr.ac.una.ventas.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBusiness {

    @Autowired
    private CategoryData categoryData;

    public List<Category> findAll() {
        return categoryData.findAll();
    }

    public Category findById(int id) {
        return categoryData.findById(id);
    }

    public void save(Category category) {
        categoryData.save(category);
    }

    public void delete(int id) {
        categoryData.delete(id);
    }
}
