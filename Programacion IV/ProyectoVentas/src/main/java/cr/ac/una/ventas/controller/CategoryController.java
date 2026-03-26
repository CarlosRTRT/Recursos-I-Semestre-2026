/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.ventas.controller;

/**
 *
 * @author carlosrodriguez
 */

import cr.ac.una.ventas.business.CategoryBusiness;
import cr.ac.una.ventas.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryBusiness categoryBusiness;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", categoryBusiness.findAll());
        return "category/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("formTitle", "Nueva Categoría");
        return "category/formCategory";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model) {
        Category category = categoryBusiness.findById(id);
        model.addAttribute("category", category);
        model.addAttribute("formTitle", "Editar Categoría");
        return "category/formCategory";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category) {
        categoryBusiness.save(category);
        return "redirect:/categories/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        categoryBusiness.delete(id);
        return "redirect:/categories/";
    }
}
