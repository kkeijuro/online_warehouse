package cat.uab.tq.online_warehouse;

import java.util.List;

import cat.uab.tq.online_warehouse.articles.Article;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class ShopManager {
    private ArticlesAccess _articlesAccess;
    private ClientAccess _clientAccess;
    private PaymentPlatform _paymentPlatform;
    private OrderManagement _orderManagement;
    
    ShopManager(ArticlesAccess articlesAccess,
                ClientAccess clientAccess,
                PaymentPlatform paymentPlatform,
                OrderManagement orderManagement) {
        _articlesAccess = articlesAccess;
        _clientAccess = clientAccess;
        _paymentPlatform = paymentPlatform;
        _orderManagement = orderManagement;
    }

    public List<Article> getArticles() {
        return _articlesAccess.getArticlesAvailable();
    }

    public Order createOrder() {
        Order newOrder = new Order(_articlesAccess, _clientAccess);
        return newOrder;
    }

    public String completeOrder(Order order) {
        String orderId = _orderManagement.save(order);
        return orderId;
    }

    public Order getOrder(String orderId) {
        return _orderManagement.get(orderId);
    }

    public void cancelOrder(String orderId) {
        Order order = _orderManagement.get(orderId);
        order.cancel();
    }
    
    public void prepaidOrder(Order order) {
        if (order.calculateTotalPrice() >= 400) {
            _paymentPlatform.pay(order);
            order.isPaid();
        }
    }
}
