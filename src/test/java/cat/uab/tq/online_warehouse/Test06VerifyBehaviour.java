package cat.uab.tq.online_warehouse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cat.uab.tq.online_warehouse.articles.Article;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Test06VerifyBehaviour {
    
    private ShopManager _shopManager;
    private ArticlesAccess _articleAccess;
    private ClientAccess _client_access;
    private PaymentPlatform _paymentPlatform;
    private OrderManagement _orderManagement;

    @BeforeEach
    public void setUp() {
        _articleAccess = mock(ArticlesAccess.class);
        _client_access = mock(ClientAccess.class);
        _paymentPlatform = mock(PaymentPlatform.class);
        _orderManagement = mock(OrderManagement.class);
        _shopManager = new ShopManager(_articleAccess, 
                                        _client_access, 
                                        _paymentPlatform, 
                                        _orderManagement);
    }

    @Test
    public void whenOrderIsEqualTo400PayShouldBeDone() {
        // given
        Order order = _shopManager.createOrder();
        Article article1 = new Article("Article 1", "Article 1 Description", 10.0, "12343212");
        Article article2 = new Article("Article 2", "Article 2 Description", 30.0, "12343215");
        when(_articleAccess.getArticle(any(), anyInt())).thenReturn(article1).thenReturn(article2);
        // when
        order.addArticle("12343212", 10);
        order.addArticle("12343215", 10);
        // then
        assert(order.calculateTotalPrice() == 400.0);
        _shopManager.prepaidOrder(order);
        verify(_paymentPlatform, times(1)).pay(order);
        verifyNoMoreInteractions(_paymentPlatform);
    }

    @Test
    public void whenOrderIsLessThan400PayShouldBeDone() {
        // given
        Order order = _shopManager.createOrder();
        Article article1 = new Article("Article 1", "Article 1 Description", 10.0, "12343212");
        Article article2 = new Article("Article 2", "Article 2 Description", 29.9, "12343215");
        when(_articleAccess.getArticle(any(), anyInt())).thenReturn(article1).thenReturn(article2);
        // when
        order.addArticle("12343212", 10);
        order.addArticle("12343215", 10);
        // then
        assert(order.calculateTotalPrice() == 399.0);

        _shopManager.prepaidOrder(order);
        verifyNoInteractions(_paymentPlatform);
    }
}
