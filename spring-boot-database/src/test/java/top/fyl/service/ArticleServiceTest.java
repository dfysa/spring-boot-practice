package top.fyl.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.fyl.springboot.database.entity.Article;
import top.fyl.springboot.database.service.ArticleService;

import java.util.List;

/**
 * @author dfysa
 * @data 2024/10/8 下午3:08
 * @description
 */
@SpringBootTest
@Slf4j
class ArticleServiceTest {
    @Resource
    private ArticleService articleService;
    @Test
    void saveArticle() {
        Article article = Article.builder()
                .title("SpringBoot")
                .author("张三")
                .content("SpringBoot 从入门到精通")
                .build();
        Article savedArticle = articleService.saveArticle(article);
        log.info(String.valueOf(savedArticle));
    }


    @Test
    void updateArticle() {
        Article article = articleService.getArticle(1L);
        article.setTitle("SpringBoot111");
        articleService.updateArticle(article);
    }

    @Test
    void getArticle() {
        Article article = articleService.getArticle(1L);
        log.info(String.valueOf(article));
    }

    @Test
    void getAll() {
        List<Article> articles=articleService.getAll();
        articles.forEach(article -> log.info(String.valueOf(article)));
    }

    @Test
    void deleteArticle() {
        articleService.deleteArticle(3L);
    }

}