package gc.board.article.controller;

import gc.board.article.service.ArticleService;
import gc.board.article.service.request.ArticleCreateRequest;
import gc.board.article.service.request.ArticleUpdateRequest;
import gc.board.article.service.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//클라이언트와 api 통신을 주고받는 기능을 함.
//매개체: json -> rest!!!
@RestController
//생성자 어노테이션
@RequiredArgsConstructor
public class ArticleController {
  //의존성
  private final ArticleService articleService;

  //읽기 api
  @GetMapping("/v1/articles/{articleId}")
  public ArticleResponse read(@PathVariable Long articleId) {
    return articleService.read(articleId);
  }

  //생성 api
  @PostMapping("/v1/articles")
  public ArticleResponse create(@RequestBody ArticleCreateRequest request) {
    return articleService.create(request);
  }

  //수정 api
  @PutMapping("/v1/articles/{articleId}")
  public ArticleResponse update(
          @PathVariable Long articleId,
          @RequestBody ArticleUpdateRequest request
  ) {
    return articleService.update(articleId, request);
  }

  //삭제 api
  @DeleteMapping("/v1/articles/{articleId}")
  public void delete(@PathVariable Long articleId) {
    articleService.delete(articleId);
  }

}
