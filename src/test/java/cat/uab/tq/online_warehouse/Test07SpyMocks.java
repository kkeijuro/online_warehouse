package cat.uab.tq.online_warehouse;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Test07SpyMocks {
    
    private OrderManagement _orderManagement;
    private ArticlesAccess _articleAccess;
    private ClientAccess _clientAccess;
    private PaymentPlatform _paymentPlatform;

    private ShopManager _shopManager;

    @BeforeEach
    public void setUp() {
        // Check Differences between mock and spy
        //_orderManagement = mock(OrderManagement.class);
        _orderManagement = spy(OrderManagement.class);
        _articleAccess = mock(ArticlesAccess.class);
        _clientAccess = mock(ClientAccess.class);
        _shopManager = new ShopManager(_articleAccess,
                                       _clientAccess,
                                       _paymentPlatform,
                                       _orderManagement);
    }

    @Test
    public void shouldCompleteBookingWhenInputOK() {
          // given
          Order order = new Order(_articleAccess, _clientAccess);
          // when
          String orderId = _shopManager.completeOrder(order);
          // then
          // Check what is printed if the _orderManagement is mocked instead of spied
          System.out.println("Order saved with id: " + orderId);

    }

    @Test
    public void shouldCancelWhenIsAlreadyCreated() {
        // given
        Order order = new Order(_articleAccess, _clientAccess);
        String orderId = _shopManager.completeOrder(order);
        // when
        _shopManager.cancelOrder(orderId);
        // then
    }

    @Test
    public void shouldCancelWhenIsAlreadyCreatedForceOrderId() {
        // given
        Order order = new Order(_articleAccess, _clientAccess);
        _shopManager.completeOrder(order);
        String orderId = "1";
        // when
        // Force spy to return order
        doReturn(order).when(_orderManagement).get(orderId);
        //Its the same as:
        //when(_orderManagement.get(orderId)).thenReturn(order);
        _shopManager.cancelOrder(orderId);
        // then
    }
}
