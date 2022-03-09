package Data.Person;

import Logic.Person.Person;
import Logic.Person.TPerson;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

import java.util.ArrayList;
import java.util.List;

public class PersonData {
    private final static String URL = "mongodb+srv://MongoDB-GPS:ZUCUvvYbKW@gps.iox2a.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
    private final static String DATABASE = "GPS";

    public ObjectId add(TPerson tPerson) {
        try(MongoClient mongoClient = MongoClients.create(URL)) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodeRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            MongoDatabase db = mongoClient.getDatabase(DATABASE).withCodecRegistry(pojoCodeRegistry);
            Person person = new Person(tPerson);

            if(!reactivate(tPerson, db.getCollection("personas", Person.class))) {
                db.getCollection("personas", Person.class).insertOne(person);
            }

        } catch (MongoException e) {
            return null;
        }
        return tPerson.getId();
    }

    public ObjectId delete(ObjectId _id) {
        try(MongoClient mongoClient = MongoClients.create(URL)) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodeRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            MongoDatabase db = mongoClient.getDatabase(DATABASE).withCodecRegistry(pojoCodeRegistry);

            MongoCollection<Person> collection = db.getCollection("personas", Person.class);

            List<Person> personas = new ArrayList<Person>();
            collection.find(eq("_id", _id)).into(personas);

            if(personas.size() == 1){
                Person persona = new Person(personas.get(0).toTransfer());
                persona.setActivo(false);
                collection.replaceOne(eq("_id", _id), persona);
            }
        } catch (MongoException e) {
            return null;
        }
        return _id;
    }

    public List<TPerson> readAll() {
        try(MongoClient mongoClient = MongoClients.create(URL)) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodeRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            MongoDatabase db = mongoClient.getDatabase(DATABASE).withCodecRegistry(pojoCodeRegistry);

            List<TPerson> ps = new ArrayList<>();
            FindIterable<Person> iter = db.getCollection("personas", Person.class).find();
            for(Person p : iter) {
                ps.add(p.toTransfer());
            }
            return ps;
        } catch (MongoException e) {
            return null;
        }
    }

    public ObjectId update(TPerson tPerson) {
        try(MongoClient mongoClient = MongoClients.create(URL)) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodeRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            MongoDatabase db = mongoClient.getDatabase(DATABASE).withCodecRegistry(pojoCodeRegistry);

            MongoCollection<Person> collection = db.getCollection("personas", Person.class);

            List<Person> personas = new ArrayList<Person>();
            collection.find(eq("_id", tPerson.getId())).into(personas);

            if(personas.size() == 1){
                Person persona = new Person(personas.get(0).toTransfer());
                if(tPerson.getNif() != null) persona.setNif(tPerson.getNif());
                if(tPerson.getNombre() != null) persona.setNombre(tPerson.getNombre());
                if(tPerson.getApellidos() != null) persona.setApellidos(tPerson.getApellidos());

                collection.replaceOne(eq("_id", personas.get(0).getId()), persona);
            }
        } catch (MongoException e) {
            return null;
        }
        return null;
    }

    private boolean reactivate(TPerson tPerson, MongoCollection<Person> collection) {
        List<Person> personas = new ArrayList<Person>();
        collection.find(eq("nif", tPerson.getNif())).into(personas);
        if(personas.size() == 1){
            Person persona = new Person(personas.get(0).toTransfer());
            persona.setActivo(true);
            collection.replaceOne(eq("_id", personas.get(0).getId()), persona);
            return true;
        }
        return false;
    }
}
