package projet.blog.services;

import org.springframework.stereotype.Service;
import projet.blog.entities.Article;

import java.util.List;

@Service
public interface ArticleService {
    List<Article> getAllArticles();

    Article saveArticle(Article article);

    Article getArticleById(Long id);

    void deleteArticle(Long id);
}
