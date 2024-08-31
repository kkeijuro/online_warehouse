package cat.uab.tq.online_warehouse;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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

    public Article getArticle(String serialNumber) {
        // Find the article by serial number
        Document query = new Document("serialNumber", serialNumber);
        Document result = collection.find(query).first();
        if (result != null) {
            // Convert the Document to an Article object
            return new Article(result.getString("serialNumber"), result.getString("title"), result.getDouble("price"), result.getString("content"));
        } else {
            throw new IllegalArgumentException("Article not found");
        }
    }

    // Close the MongoDB client connection
    public void close() {
        mongoClient.close();
    }
}

