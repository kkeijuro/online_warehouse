package cat.uab.tq.online_warehouse.articles;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class ArticlesAccess {
    private final String MONGO_URI = "mongodb://localhost:27017";
    private final String DATABASE = "articlesDB";
    private final String COLLECTION = "articles";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public ArticlesAccess() {
        // Connect to MongoDB server
        mongoClient = MongoClients.create(MONGO_URI);
        // Connect to the database
        database = mongoClient.getDatabase(DATABASE);
        // Connect to the collection
        collection = database.getCollection(COLLECTION);
    }

    public Article getArticle(String serialNumber, int quantity) {
        // Find the article by serial number
        Document query = new Document("serialNumber", serialNumber);
        Document result = collection.find(query).first();
        if(result == null) {
            throw new ArticleNotFoundException("Article not found");
        }
        if(quantity > result.getInteger(result, quantity)) {
            throw new NotEnoughArticlesException("Not enough articles");
        }
        updateDocumentQuantity(result, quantity);
        return new Article(result.getString("serialNumber"), 
                               result.getString("title"),
                               result.getDouble("price"),
                               result.getString("content"));
    }

    public void updateArticleQuantity(String serialNumber, Integer newQuantity) {
        // Build the query to find the article by serial number
        Document query = new Document("serialNumber", serialNumber);
        updateDocumentQuantity(query, newQuantity);
    }

    private void updateDocumentQuantity(Document articleDocument, int newQuantity) {
        Document update = new Document("$set", new Document("quantity", newQuantity));
        // Apply the update
        UpdateResult result = collection.updateOne(articleDocument, update);
        if (result.getMatchedCount() == 0) {
            throw new IllegalArgumentException("Article not found");
        }
    }

    public List<Article> getArticlesAvailable() {
        // Find all articles
        List<Article> articles = new ArrayList<>();
        for (Document doc : collection.find()) {
            articles.add(new Article(doc.getString("serialNumber"), 
                                     doc.getString("title"),
                                     doc.getDouble("price"),
                                     doc.getString("content")));
        }
        return articles;
    }

    // Close the MongoDB client connection
    public void close() {
        mongoClient.close();
    }
}

