package top.fyl.springboot.database.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Article;
import top.fyl.springboot.database.repository.ArticleRepository;
import top.fyl.springboot.database.service.ArticleService;

import java.util.List;
import java.util.Optional;

/**
 * @author dfysa
 * @data 8/10/2024 下午2:22
 * @description
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleRepository articleRepository;
    @Override
    public Article saveArticle(Article article) {
        articleRepository.save(article);
        return article;
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);

    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }
    @Override
    public Article getArticle(Long id){
        Optional<Article> optionalArticle=articleRepository.findById(id);
        return optionalArticle.orElse(null);}

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }
}
