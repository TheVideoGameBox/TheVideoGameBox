package Data;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ConnectionImp extends Connection {
    @Override
    public MongoDatabase getConnection() throws MongoException {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://MongoDB-GPS:ZUCUvvYbKW@gps.iox2a.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).codecRegistry(pojoCodecRegistry).build();

        MongoClient mongoClient = MongoClients.create(clientSettings);
        return mongoClient.getDatabase("TVGB").withCodecRegistry(pojoCodecRegistry);
    }
}