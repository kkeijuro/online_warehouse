package cat.uab.tq.online_warehouse;

import java.util.List;

import cat.uab.tq.online_warehouse.articles.Article;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class ShopManager {
    ArticlesAccess _articlesAccess;
    ClientAccess _clientAccess;
    
    ShopManager(ArticlesAccess articlesAccess, ClientAccess clientAccess) {
        _articlesAccess = articlesAccess;
        _clientAccess = clientAccess;
    }

    public List<Article> getArticles() {
        return _articlesAccess.getArticlesAvailable();
    }

}
