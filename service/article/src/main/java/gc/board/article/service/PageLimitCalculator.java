package gc.board.article.service;

//경우에 따라 301, 601, 901 등으로 계산해주는 유틸리티 클래스

import gc.board.article.entity.Article;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageLimitCalculator {
    public static Long caculatePageLimit(Long page, Long pageSize, Long movablePageCount) {
     return (((page-1) / movablePageCount) + 1) * pageSize * movablePageCount + 1;
    }
}
