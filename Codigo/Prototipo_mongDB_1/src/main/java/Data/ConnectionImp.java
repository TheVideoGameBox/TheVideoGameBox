package Data;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectionImp extends Connection {
    private Connection con;

    @Override
    public MongoDatabase getConnection() throws ClassNotFoundException, MongoException {
        MongoClient mongoClient = MongoClients.create("mongodb+srv://MongoDB-GPS:ZUCUvvYbKW@gps.iox2a.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        return mongoClient.getDatabase("GPS");

    }
}
