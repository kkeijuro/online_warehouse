package cat.uab.tq.online_warehouse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cat.uab.tq.online_warehouse.articles.Article;
import cat.uab.tq.online_warehouse.articles.ArticlesAccess;
import cat.uab.tq.online_warehouse.clients.ClientAccess;

public class Test03ReturnValues {
    
    private ShopManager _shop_manager;
    private ArticlesAccess _article_access;
    private ClientAccess _client_access;

    @BeforeEach
    public void setUp() {
        _article_access = mock(ArticlesAccess.class);
        _client_access = mock(ClientAccess.class);
        _shop_manager = new ShopManager(_article_access, _client_access);
    }

    @Test
    public void shouldReturnLoadedValuesWhenCalledOnce() {
        // given
        when(_article_access.getArticlesAvailable()).thenReturn(
                                List.of(
                                    new Article("Article 1", "Article 1 Description", 10.0, "12343212"),
                                    new Article("Article 2", "Article 2 Description", 20.0, "43542443"),
                                    new Article("Article 3", "Article 3 Description", 30.0, "78214345")));
        // when
        List<Article> values = _shop_manager.getArticles();
        // then
         assertTrue(values.size() == 3, "The list should have 3 elements");

    }

    @Test
    public void shouldReturnLoadedValuesWhenCalledMultipleTimes() {
        // given
        when(_article_access.getArticlesAvailable())
                                .thenReturn(List.of(
                                    new Article("Article 1", "Article 1 Description", 10.0, "12343212")))
                                .thenReturn(List.of(
                                            new Article("Article 1", "Article 1 Description", 10.0, "12343212"),
                                            new Article("Article 2", "Article 2 Description", 20.0, "43542443"),
                                            new Article("Article 3", "Article 3 Description", 30.0, "78214345")));
        // when
        List<Article> firstValues = _shop_manager.getArticles();
        List<Article> secondValues = _shop_manager.getArticles();
        // then
         assertTrue(firstValues.size() == 1, "The list should have 1 element");
         assertTrue(secondValues.size() == 3, "The list should have 3 elements");
    }
}
