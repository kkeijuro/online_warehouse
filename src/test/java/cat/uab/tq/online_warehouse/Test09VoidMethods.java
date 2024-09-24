package cat.uab.tq.online_warehouse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;

import cat.uab.tq.online_warehouse.articles.ArticleNotFoundException;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Test09VoidMethods {
    
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
    public void testWhenNoPresentArticlesRemovedThenExceptionRaised() {
        //given
        // -> This cannot be done with mockito, as it does not support void methods
        //when(_articleAccess.updateArticleQuantity("12343212", 200))
        //     .thenThrow(new ArticleNotFoundException("12343212"));
        doThrow(new ArticleNotFoundException("")).when(_articleAccess).updateArticleQuantity(any(), anyInt());
        //when
        Executable executable = () -> _order.removeArticles("12343212", 200);
        //then
        assertThrows(ArticleNotFoundException.class, executable);
    }
}
