package top.fyl.springboot.database.service;


import org.springframework.stereotype.Service;
import top.fyl.springboot.database.entity.Article;

import java.util.List;

/**
 * @author dfysa
 * @data 8/10/2024 下午2:21
 * @description
 */
@Service
public interface ArticleService {
    Article saveArticle(Article article);

    void deleteArticle(Long id);

    void updateArticle(Article article);

    List<Article> getAll();

    public Article getArticle(Long id);


 }
