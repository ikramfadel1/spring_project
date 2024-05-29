package projet.blog.services;

import projet.blog.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void saveCategory(Category category);
    void deleteCategoryById(Long id);
}
