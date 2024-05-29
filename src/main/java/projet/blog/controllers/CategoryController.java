package projet.blog.controllers;

import org.springframework.ui.ModelMap;
import projet.blog.entities.Article;
import projet.blog.entities.Category;
import projet.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller

public class CategoryController {
    @Autowired
    private  CategoryService categoryService;


    @GetMapping("/listCA")
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "CategoryList";
    }

    @GetMapping("/createCA")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());

        return "CreateCategory";
    }

    @PostMapping("/saveCA")
    public String saveCategory(@ModelAttribute("category") String categoryName,
                               @RequestParam("categoryParent") String categoryParent) {
        Category category = new Category();
        category.setCategory(categoryName); // Utilisez le nom de la catégorie fourni par le formulaire
        category.setCategoryParent(categoryParent);
        categoryService.saveCategory(category);
        return "redirect:/listCA";
    }


    @GetMapping("/editCA")
    public String showEditCategoryForm(@RequestParam Long id, ModelMap modelMap) {
        Category category = categoryService.getCategoryById(id);
        modelMap.addAttribute("category", category);
        return "EditCategory";
    }

    @PostMapping("/updateCA")
    public String updateCategory(@RequestParam("id") Long categoryId,
                                 @RequestParam("category") String categoryName,
                                 @RequestParam("categoryParent") String categoryParent) {
        // Récupérer la catégorie à mettre à jour à partir de l'ID
        Category updatedCategory = categoryService.getCategoryById(categoryId);

        // Mettre à jour les propriétés de la catégorie
        updatedCategory.setCategory(categoryName);
        updatedCategory.setCategoryParent(categoryParent);

        // Enregistrer la catégorie mise à jour
        categoryService.saveCategory(updatedCategory);

        // Rediriger vers la page qui liste les catégories après la mise à jour
        return "redirect:/listCA";
    }

    @GetMapping("/deleteCA/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/listCA";
    }
}