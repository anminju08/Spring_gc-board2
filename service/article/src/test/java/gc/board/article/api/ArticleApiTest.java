package gc.board.article.api;

import gc.board.article.service.response.ArticlePageResponse;
import gc.board.article.service.response.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {
  RestClient restClient = RestClient.create("http://localhost:9000");

  @Test
  void createTest() {
    ArticleResponse response = create(new ArticleCreateRequest(
            "hi", "my content", 1L, 1L
    ));
    System.out.println("response = " + response);
  }

  ArticleResponse create(ArticleCreateRequest request){
    return restClient.post()
            .uri("/v1/articles")
            .body(request)
            .retrieve()
            .body(ArticleResponse.class);
  }
  
  // 생성된 article_id: 229193736368320512
  
  @Test
  void readTest() {
    ArticleResponse response = read(229193736368320512L);
    System.out.println("response = " + response);
  }

  ArticleResponse read(Long articleId){
    return restClient.get()
            .uri("/v1/articles/{articleId}", articleId)
            .retrieve()
            .body(ArticleResponse.class);
  }

  @Test
  void readAllTest() {
      ArticlePageResponse response = restClient.get()
              .uri("/v1/articles?boardId=1&page=1&pageSize=30")
              .retrieve()
              .body(ArticlePageResponse.class);
      System.out.println("response.getArticleCount() = " + response.getArticleCount());
      for(ArticleResponse article : response.getArticles()){
          System.out.println("articleId = " + article.getArticleId());
      }
  }

  //수정 테스트코드
  @Test
  void updateTest() {
    ArticleResponse response = update(
            229193736368320512L, new ArticleUpdateRequest(
                    "hi 2", "my content 2")
    );
    System.out.println("response = " + response);
  }

  ArticleResponse update(Long articleId, ArticleUpdateRequest request){
    return restClient.put()
            .uri("/v1/articles/{articleId}", articleId)
            .body(request)
            .retrieve()
            .body(ArticleResponse.class);
  }

  //삭제 테스트코드
  @Test
  void deleteTest() {
    restClient.delete()
            .uri("/v1/articles/{articleId}", 229193736368320512L)
            .retrieve();
  }

  @Getter
  @ToString
  @AllArgsConstructor
  static class ArticleCreateRequest {
    private String title;
    private String content;
    private Long boardId;
    private Long writerId;
  }

  @Getter
  @ToString
  @AllArgsConstructor
  static class ArticleUpdateRequest {
    private String title;
    private String content;
  }
}
