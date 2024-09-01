package cat.uab.tq.online_warehouse;

import java.util.HashMap;
import java.util.Map;

import cat.uab.tq.online_warehouse.articles.Article;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.Client;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Order {
    
    private String _orderId;
    private ArticlesAccess _articleAccess;
    private ClientAccess _clientAccess;
    private Client _client;
    private Map<Article, Double> _articles = new HashMap<>();
    
    public Order(String orderId, 
                 ArticlesAccess articleAccess,
                 ClientAccess clientAccess) {
        _orderId = orderId;
    }

    public Client setClientId(String clientId) {
        _client = _clientAccess.getClient(clientId);
        return _client;
    }
    
    public String getOrderId() {
        return _orderId;
    }

    public void addArticle(String articleSn, Double quantity) {
        Article article = _articleAccess.getArticle(articleSn);
        _articles.put(article, quantity);
    }

    public void removeArticle(String articleSn) {
        Article article = _articleAccess.getArticle(articleSn);
        _articles.remove(article);
    }
    
    public Double calculateTotalPrice() {
        double price = 0.0;
        for (Map.Entry<Article, Double> entry : _articles.entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        return price;
    }
}
