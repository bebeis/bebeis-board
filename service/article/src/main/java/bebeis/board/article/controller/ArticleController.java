package bebeis.board.article.controller;

import bebeis.board.article.service.ArticleService;
import bebeis.board.article.service.request.ArticleCreateRequest;
import bebeis.board.article.service.request.ArticleUpdateRequest;
import bebeis.board.article.service.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("v1/articles/{articleId}")
    public ArticleResponse read(@PathVariable Long articleId) {
        log.info("{} read request", articleId);
        return articleService.read(articleId);
    }

    @PostMapping("/v1/articles")
    public ArticleResponse create(@RequestBody ArticleCreateRequest request) {
        return articleService.create(request);
    }

    @PutMapping("/v1/articles/{articleId}")
    public ArticleResponse update(@PathVariable Long articleId, @RequestBody ArticleUpdateRequest request) {
        return articleService.update(articleId, request);
    }

    @DeleteMapping("/v1/articles/{articleId}")
    public void delete(@PathVariable Long articleId) {
        articleService.delete(articleId);
        log.info("{} article delete", articleId);
    }
}
