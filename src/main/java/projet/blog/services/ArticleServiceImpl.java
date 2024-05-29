package projet.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.blog.entities.Article;
import projet.blog.repositories.ArticleRepository;

import java.util.List;

@Service
public class ArticleServiceImpl  implements ArticleService{
    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    @Override
    public Article saveArticle(Article article) {
        articleRepository.save(article);
        return article;
    }
    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
