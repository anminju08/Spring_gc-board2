package gc.board.article.service.response;

import gc.board.article.entity.Article;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ArticlePageResponse {
    private Long articleCount;
    private List<ArticleResponse> articles;



    public static ArticlePageResponse of(
            List<ArticleResponse> articles, Long articleCount
    ){
        ArticlePageResponse response = new ArticlePageResponse();
        response.articles = articles;
        response.articleCount = articleCount;
        return response;
    }
}
