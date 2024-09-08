package cat.uab.tq.online_warehouse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cat.uab.tq.online_warehouse.articles.Article;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Test02DefaultValues {
    
    private ShopManager _shop_manager;
    private ArticlesAccess _article_access;
    private ClientAccess _client_access;

    @BeforeEach
    public void setUp() {
        _article_access = mock(ArticlesAccess.class);

        _shop_manager = new ShopManager(_article_access, _client_access);
    }

    @Test
    public void shouldReturnEmptyList() {
        // given
        // when
        List<Article> values = _shop_manager.getArticles();
        // then
         assertTrue(values.isEmpty(), "The list should be empty");

    }
}
