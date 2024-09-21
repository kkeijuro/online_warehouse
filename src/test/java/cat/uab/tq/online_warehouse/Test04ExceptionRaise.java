package cat.uab.tq.online_warehouse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import cat.uab.tq.online_warehouse.articles.ArticleNotFoundException;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Test04ExceptionRaise {
    
    private Order _order;
    private ArticlesAccess _articleAccess;
    private ClientAccess _clientAccess;

    @BeforeEach
    public void setUp() {
        _articleAccess = mock(ArticlesAccess.class);
        _clientAccess = mock(ClientAccess.class);
        
        _order = new Order(_articleAccess, _clientAccess);
    }

    @Test
    public void whenArticleDoesntExistShouldReturnException() {
        // given
        when(_articleAccess.getArticle("12343212", 2)).thenThrow(new ArticleNotFoundException("Article not found"));
        // when
        Executable executable = () -> _order.addArticle("12343212", 2);
        // then
        assertThrows(ArticleNotFoundException.class, executable);
    }
}
