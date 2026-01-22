package bebeis.board.article.repository;

import bebeis.board.article.entity.Article;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Slf4j
@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void findAllTest() {
        long startMs = System.currentTimeMillis();
        List<Article> articles = articleRepository.findAll(1L, 4999980L, 30L);
        long endMs = System.currentTimeMillis();
        log.info("수행 시간: " + (endMs - startMs) + "ms");
        log.info("articles.size() = {}", articles.size());
        log.info("articles.getLast().getArticleId() = {}", articles.getLast().getArticleId());
    }

    @Test
    void findAllQueryMethodTest() {
        PageRequest pageRequest = PageRequest.of(
                5000000 / 30,
                30,
                Sort.by(Sort.Direction.DESC, "articleId")
        );

        long startMs = System.currentTimeMillis();
        List<Article> articles = articleRepository.findAllByBoardId(1L, pageRequest);
        long endMs = System.currentTimeMillis();
        log.info("수행 시간: " + (endMs - startMs) + "ms");
        log.info("articles.size() = {}", articles.size());
        log.info("articles.getLast().getArticleId() = {}", articles.getLast().getArticleId());
    }

    @Test
    void countTest() {
        Long count = articleRepository.count(1L, 10000L);
        log.info("count = {}", count);
    }

    @Test
    void findInfiniteScrollTest() {
        List<Article> articles = articleRepository.findAllInfiniteScroll(1L, 30L);
        for (Article article : articles) {
            log.info("article.getArticleId() = {}", article.getArticleId());
        }

        Long lastArticleId = articles.getLast().getArticleId();
        List<Article> articles2 = articleRepository.findAllInfiniteScroll(1L, 30L, lastArticleId);
        for (Article article : articles2) {
            log.info("article.getArticleId() = {}", article.getArticleId());
        }
    }

}