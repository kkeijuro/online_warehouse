package cat.uab.tq.online_warehouse.clients;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ClientAccess {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public ClientAccess() {
        // Connect to MongoDB server
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Connect to the database
        database = mongoClient.getDatabase("onlineWarehouseDB");
        // Connect to the collection
        collection = database.getCollection("clients");
    }

    // Create a new client document
    public void createClient(String clientId, String name, String email) {
        Document doc = new Document("clientId", clientId)
                        .append("name", name)
                        .append("email", email);
        collection.insertOne(doc);
    }

    public Client getClient(String clientId) {
        Document query = new Document("clientId", clientId);
        Document result = collection.find(query).first();
        if (result == null) {
            throw new ClientNotFoundException(
                                String.format("Client not found %s", clientId));
        }
        else {
            return new Client(result.getString("name"),
                              result.getString("clientId"),
                              result.getString("address"),
                              result.getString("email"),
                              result.getString("phone"));
        }
    }

    public void deleteClient(String clientId) {
        Document query = new Document("clientId", clientId);
        collection.deleteOne(query);
    }

    public void close() {
        mongoClient.close();
    }
}