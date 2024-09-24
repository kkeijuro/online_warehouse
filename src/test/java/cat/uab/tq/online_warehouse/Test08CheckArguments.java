package cat.uab.tq.online_warehouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import cat.uab.tq.online_warehouse.articles.Article;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Test08CheckArguments {
    
    private Order _order;
    private ArticlesAccess _articleAccess;
    private ClientAccess _clientAccess;
    ArgumentCaptor<Integer> _quantityCaptor;

    @BeforeEach
    public void setUp() {
        // Check Differences between mock and spy
        _articleAccess = mock(ArticlesAccess.class);
        _clientAccess = mock(ClientAccess.class);
        _order = new Order(_articleAccess, _clientAccess);
        _quantityCaptor = ArgumentCaptor.forClass(Integer.class);
    }

    @Test
    public void testWhenArticlesRemovedThenUpdateArticleQuantity() {
        //given
        when(_articleAccess.getArticle("12343212", 200))
                .thenReturn(new Article("Article 1", "Article 1 Description", 10.0, "12343212")); 
        _order.addArticle("12343212", 200);
        //when
        _order.removeArticles("12343212", 50);
        //then
        verify(_articleAccess).updateArticleQuantity(eq("12343212"), _quantityCaptor.capture());
        Integer quantity = _quantityCaptor.getValue();
        assert(quantity == 150);
    }
    
    @Test
    public void testWhenArticlesRemovedMultipleTimesThenUpdateArticleQuantity() {
        //given
        when(_articleAccess.getArticle("12343212", 200))
                .thenReturn(new Article("Article 1", "Article 1 Description", 10.0, "12343212")); 
        _order.addArticle("12343212", 200);
        List<Integer> expectedQuantities = List.of(150, 100);
        //when
        _order.removeArticles("12343212", 50);
        _order.removeArticles("12343212", 50);
        //then
        verify(_articleAccess, times(2)).updateArticleQuantity(eq("12343212"), _quantityCaptor.capture());
        List<Integer> quantities = _quantityCaptor.getAllValues();
        assertEquals(quantities, expectedQuantities);
    }
}
