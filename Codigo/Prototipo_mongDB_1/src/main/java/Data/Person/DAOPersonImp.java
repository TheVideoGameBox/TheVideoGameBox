package Data.Person;

import Logic.Person.Person;
import Logic.Person.TPerson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

import java.util.Collections;
import java.util.List;

public class DAOPersonImp implements DAOPerson {
    private final static String URL = "mongodb+srv://MongoDB-GPS:ZUCUvvYbKW@gps.iox2a.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
    private final static String DATABASE = "GPS";

    @Override
    public String add(TPerson tPerson) {
        try(MongoClient mongoClient = MongoClients.create(URL)) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodeRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            MongoDatabase db = mongoClient.getDatabase(DATABASE).withCodecRegistry(pojoCodeRegistry);
            Person person = new Person(tPerson);

            db.getCollection("personas", Person.class).insertOne(person);
        } catch (Exception e) {
            return "";
        }

        return "";
    }

    @Override
    public int delete(String id) {
        return -1;
    }

    @Override
    public TPerson read(String id) {
        return null;
    }

    @Override
    public List<TPerson> readAll() {
        try(MongoClient mongoClient = MongoClients.create(URL)) {
            MongoDatabase db = mongoClient.getDatabase(DATABASE);
            db.getCollection("personas", Person.class);
        } catch (Exception e) {
            return null;
        }

        return Collections.emptyList();
    }

    @Override
    public int update(TPerson tPerson) {
        return 0;
    }
}
