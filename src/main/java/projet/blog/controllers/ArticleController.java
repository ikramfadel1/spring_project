package projet.blog.controllers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projet.blog.entities.Article;
import projet.blog.entities.Category;
import projet.blog.services.ArticleService;
import projet.blog.services.CategoryService;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    private CategoryController Controller;
@Autowired
private CategoryService categoryService;
    @GetMapping("/Home")
    public String home(ModelMap modelMap) {
        List<Article> articles = articleService.getAllArticles();
        modelMap.addAttribute("articles", articles);
        return "Home"; // Le nom de la vue Thymeleaf à afficher (home.html dans votre cas)
    }
    @GetMapping("/createArticle")
    public String showCreateArticleForm(Model modelMap ) {
        Controller.listCategories(modelMap);
        return "CreateArticle";
    }

    @PostMapping("/saveArticle")
    public String saveArticle(@ModelAttribute ("articlevue") Article article) {

        Article article1= articleService.saveArticle(article);
        return "redirect:/Home"; // Redirect to home page after saving the article
    }



    @GetMapping("/article/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/";
    }
    @PostMapping("/article/delete/{id}")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        articleService.deleteArticle(id);
        // Ajoutez un message de succès pour afficher après la redirection
        redirectAttributes.addFlashAttribute("success", "Article deleted successfully");
        return "redirect:/";
    }
    @GetMapping("/editArticle")
    public String showEditArticleForm(@RequestParam Long id, ModelMap modelMap) {
        Article article = articleService.getArticleById(id);
        modelMap.addAttribute("article", article);
        return "EditArticle"; // Nom du fichier HTML Thymeleaf
    }

    @PostMapping("/updateArticle")
    public String updateArticle(@RequestParam("id") Long articleId,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content,
                                @RequestParam("price") Double price,
                                @RequestParam("status") String status,
                                @RequestParam("category") Long categoryId) {
        // Récupérer l'article à mettre à jour à partir de l'ID
        Article updatedArticle = articleService.getArticleById(articleId);

        // Mettre à jour les propriétés de l'article
        updatedArticle.setTitre(title);
        updatedArticle.setContent(content);
        updatedArticle.setPrice(price);
        updatedArticle.setStatus(status);
        // Mettre à jour la catégorie de l'article
        Category category = categoryService.getCategoryById(categoryId);
        updatedArticle.setCategory(category);

        // Enregistrer l'article mis à jour
        articleService.saveArticle(updatedArticle);

        // Rediriger vers la page qui liste les articles après la mise à jour
        return "redirect:/";
    }



}
