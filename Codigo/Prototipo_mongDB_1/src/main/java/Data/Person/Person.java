package Data.Person;

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

public class Person {
    private final static String URL = "mongodb+srv://MongoDB-GPS:ZUCUvvYbKW@gps.iox2a.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
    private final static String DATABASE = "GPS";

    public ObjectId add(TPerson tPerson) {
        try(MongoClient mongoClient = MongoClients.create(URL)) {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodeRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            MongoDatabase db = mongoClient.getDatabase(DATABASE).withCodecRegistry(pojoCodeRegistry);
            Logic.Person.Person person = new Logic.Person.Person(tPerson);

            if(!reactivate(tPerson, db.getCollection("personas", Logic.Person.Person.class))) {
                db.getCollection("personas", Logic.Person.Person.class).insertOne(person);
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

            MongoCollection<Logic.Person.Person> collection = db.getCollection("personas", Logic.Person.Person.class);

            List<Logic.Person.Person> personas = new ArrayList<Logic.Person.Person>();
            collection.find(eq("_id", _id)).into(personas);

            if(personas.size() == 1){
                Logic.Person.Person persona = new Logic.Person.Person(personas.get(0).toTransfer());
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
            FindIterable<Logic.Person.Person> iter = db.getCollection("personas", Logic.Person.Person.class).find();
            for(Logic.Person.Person p : iter) {
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

            MongoCollection<Logic.Person.Person> collection = db.getCollection("personas", Logic.Person.Person.class);

            List<Logic.Person.Person> personas = new ArrayList<Logic.Person.Person>();
            collection.find(eq("_id", tPerson.getId())).into(personas);

            if(personas.size() == 1){
                Logic.Person.Person persona = new Logic.Person.Person(personas.get(0).toTransfer());
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

    private boolean reactivate(TPerson tPerson, MongoCollection<Logic.Person.Person> collection) {
        List<Logic.Person.Person> personas = new ArrayList<Logic.Person.Person>();
        collection.find(eq("nif", tPerson.getNif())).into(personas);
        if(personas.size() == 1){
            Logic.Person.Person persona = new Logic.Person.Person(personas.get(0).toTransfer());
            persona.setActivo(true);
            collection.replaceOne(eq("_id", personas.get(0).getId()), persona);
            return true;
        }
        return false;
    }
}
