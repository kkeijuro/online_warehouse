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
    
    private ShopManager _shopManager;
    private ArticlesAccess _articleAccess;
    private ClientAccess _clientAccess;
    private PaymentPlatform _paymentPlatform;
    private OrderManagement _orderManagement;

    @BeforeEach
    public void setUp() {
        _articleAccess = mock(ArticlesAccess.class);

        _shopManager = new ShopManager(_articleAccess, 
                                        _clientAccess,
                                        _paymentPlatform,
                                        _orderManagement);
    }

    @Test
    public void shouldReturnEmptyListWhenNoArticlesAvailable() {
        // given
        // when
        List<Article> values = _shopManager.getArticles();
        // then
         assertTrue(values.isEmpty(), "The list should be empty");
    }
}
