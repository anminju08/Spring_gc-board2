package gc.board.article.repository;

import gc.board.article.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j // 로그를 남기게 해줌
@SpringBootTest
class ArticleRepositoryTest {
  @Autowired
  ArticleRepository articleRepository;

  @Test
  void findAllTest() {
    List<Article> articles = articleRepository.findAll(1L, 1499970L, 30L);
    log.info("articles.size() = {}", articles.size());
    for(Article article : articles) {
      log.info("article = {}", article);
    }
  }


}