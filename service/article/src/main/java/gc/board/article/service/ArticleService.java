package gc.board.article.service;

import gc.board.article.entity.Article;
import gc.board.article.repository.ArticleRepository;
import gc.board.article.service.request.ArticleCreateRequest;
import gc.board.article.service.request.ArticleUpdateRequest;
import gc.board.article.service.response.ArticlePageResponse;
import gc.board.article.service.response.ArticleResponse;
import kuke.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final Snowflake snowflake = new Snowflake();
  private final ArticleRepository articleRepository;

  //생성
  @Transactional
  public ArticleResponse create(ArticleCreateRequest request) {
    Article article = articleRepository.save(
            Article.create(
                    snowflake.nextId(), request.getTitle(), request.getContent(),
                    request.getBoardId(), request.getWriterId()
            )
    );
    return ArticleResponse.from(article);
  }

  //수정
  @Transactional
  public ArticleResponse update(
          Long articleId, ArticleUpdateRequest request
  ) {
    Article article = articleRepository.findById(articleId).orElseThrow();
    article.update(request.getTitle(), request.getContent());
    return ArticleResponse.from(article);
  }

  //읽기(단건 조회)
  public ArticleResponse read(Long articleId) {
    Article article = articleRepository.findById(articleId).orElseThrow();
    return ArticleResponse.from(article);
  }

  //목록 조회
    public ArticlePageResponse readAll(Long boardId, Long page, Long pageSize){
      return ArticlePageResponse.of(
              articleRepository.findAll(boardId, (page -1)*pageSize, pageSize).stream()
                      .map(ArticleResponse::from)
                      .toList(),
              articleRepository.count(
                      boardId,
                      PageLimitCalculator.caculatePageLimit(page, pageSize, 10L)
              )
      );
    }

    //무한스크롤 조회 로직
    public List<ArticleResponse> readAllInfiniteScroll(
            Long boardId, Long pageSize, Long lastArticleId
    ){
        List<Article> articles = lastArticleId == null ?
                articleRepository.findAllInfiniteScroll(boardId, pageSize) :
                articleRepository.findAllInfiniteScroll(boardId, pageSize, lastArticleId);
        return articles.stream()
                .map(ArticleResponse::from)
                .toList();
    }

  //삭제
  @Transactional
  public void delete(Long articleId) {
    articleRepository.deleteById(articleId);
  }
}
