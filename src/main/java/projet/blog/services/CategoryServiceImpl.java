package projet.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.blog.entities.Category;
import projet.blog.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
