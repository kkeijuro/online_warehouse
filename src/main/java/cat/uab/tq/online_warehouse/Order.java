package cat.uab.tq.online_warehouse;

import java.util.HashMap;
import java.util.Map;

import cat.uab.tq.online_warehouse.articles.Article;
import cat.uab.tq.online_warehouse.articles.ArticleNotFoundException;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.Client;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Order {
    public enum Status {
        OPEN,
        PROCESSING,
        COMPLETED,
        CANCELLED;
    }
    private ArticlesAccess _articleAccess;
    private ClientAccess _clientAccess;
    private Client _client;
    private Map<Article, Integer> _articles = new HashMap<>();
    private Status _status = Status.OPEN;
    
    public Order(ArticlesAccess articleAccess,
                 ClientAccess clientAccess) {
        _articleAccess = articleAccess;
        _clientAccess = clientAccess;
    }

    public Client setClientId(String clientId) {
        _client = _clientAccess.getClient(clientId);
        return _client;
    }

    public void addArticle(String articleSn, Integer quantity) {
        Article article = _articleAccess.getArticle(articleSn, quantity);
        _articles.put(article, quantity);
    }

    public void removeArticles(String articleSn, Integer quantity) {
        Article article = _articles.keySet().stream()
                .filter(a -> a.getSerialNumber().equals(articleSn))
                .findFirst()
                .orElse(null);
        if(article == null) {throw new ArticleNotFoundException(articleSn);}
        int actual_quantity = findArticleQuantity(article);
        if(actual_quantity <= quantity) {
            _articles.remove(article);
        }
        int newQuantity = actual_quantity - quantity;
        updateArticleQuantity(article, newQuantity);
        _articleAccess.updateArticleQuantity(articleSn, newQuantity);
    }
    
    public Map<Article, Integer> getArticles() {
        return _articles;
    }

    public Double calculateTotalPrice() {
        double price = 0.0;
        for (Map.Entry<Article, Integer> entry : _articles.entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        if(_client != null) {
            return price*_client.getDiscount();
        }
        return price;
    }

    private Integer findArticleQuantity(Article article) {
        if (_articles.containsKey(article)) {
            return _articles.get(article);
        } else {
            return null; // or throw an exception, or return a default value
        }
    }

    private Integer updateArticleQuantity(Article article, Integer newQuantity) {
        return _articles.put(article, newQuantity);
    }

    public void cancel() {
        if(_status == Status.COMPLETED) {
            throw new IllegalStateException("Cannot cancel a completed order");
        }
        _status = Status.CANCELLED;
    }
    public boolean isPaid() {
        return _status == Status.COMPLETED;
    }
    
    public void setPaid() {
        _status = Status.COMPLETED;
    }
}
