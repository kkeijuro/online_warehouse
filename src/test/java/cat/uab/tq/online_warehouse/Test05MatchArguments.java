package cat.uab.tq.online_warehouse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cat.uab.tq.online_warehouse.articles.Article;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Test05MatchArguments {

    private Order _order;
    private ArticlesAccess _articleAccess;
    private ClientAccess _clientAccess;

    @BeforeEach
    public void setUp() {
        _articleAccess = mock(ArticlesAccess.class);
        _clientAccess = mock(ClientAccess.class);
        
        _order = new Order("1", _articleAccess, _clientAccess);
    }

    @Test
    public void checkOrderPriceWhenAddArticle() {
        // given
        Article article = new Article("Article 1", "Article 1 Description", 10.0, "12343212");
        when(_articleAccess.getArticle("12343212", 2)).thenReturn(article);
        // when
        // -> This will not work because Article SN and quantity argument dont match
        // _order.addArticle("12343212", 5);
        // _order.addArticle("12343211", 2);
        //_order.addArticle("12343212", 3);
        _order.addArticle("12343212", 2);
        // then
        assert(_order.calculateTotalPrice() == 20.0);
    }

    @Test
    public void checkOrderPriceWhenAddMultipleArticle() {
        // given
        Article article1 = new Article("Article 1", "Article 1 Description", 10.0, "12343212");
        Article article2 = new Article("Article 1", "Article 2 Description", 10.0, "12343215");
        when(_articleAccess.getArticle(any(), anyInt())).thenReturn(article1).thenReturn(article2);
        // when
        // Check quantity can have different values because using anyInt
        _order.addArticle("12343211", 2);
        _order.addArticle("12343215", 8);
        // then
        assert(_order.calculateTotalPrice() == 100.0);
    }

    @Test
    public void checkOrderPriceWhenAddMultipleArticleAlternative() {
        // given
        Article article1 = new Article("Article 1", "Article 1 Description", 10.0, "12343212");
        Article article2 = new Article("Article 2", "Article 2 Description", 10.0, "12343215");
        when(_articleAccess.getArticle(any(), eq(4))).thenReturn(article1).thenReturn(article2);
        // when
        // Check quantity must be 4 if not test will fail
        _order.addArticle("12343211", 4);
        _order.addArticle("12343215", 4);
        // then
        assert(_order.calculateTotalPrice() == 80.0);
    }
}
