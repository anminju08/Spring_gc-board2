package gc.board.article.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageLimitCalculatorTest {
    @Test
    void caculatePageLimitTest() {
        //몇가지의 경우의 수(테스트케이스...5개정도?)
        calculatePageLimitTest(1L, 30L, 10L, 301L);
        calculatePageLimitTest(7L, 30L, 10L, 301L);
        calculatePageLimitTest(15L, 30L, 10L, 601L);
        calculatePageLimitTest(25L, 30L, 10L, 901L);
        calculatePageLimitTest(35L, 30L, 10L, 1201L);
    }

    void calculatePageLimitTest(Long page, Long pageSize, Long movablePageCount, Long expected) {
        Long result = PageLimitCalculator.caculatePageLimit(page, pageSize, movablePageCount);
        assertEquals(expected, result);

    }
}